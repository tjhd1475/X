package com.qiashe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiashe.dao.TableDao;
import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FacilityItem;
import com.qiashe.domain.Image;
import com.qiashe.service.CanteenItemsService;
import com.qiashe.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/image")
public class ImagesController {
    @Autowired
    private ImagesService service;
    @Autowired
    private CanteenItemsService canteenItemsService;
    @Autowired
    private TableDao tableDao;
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public @ResponseBody ModelAndView upload(@RequestParam("file")MultipartFile file, String canteenItemName,Integer isMainImg,HttpServletRequest request)throws IOException {
        CanteenItem findCtI= canteenItemsService.FindCanteenItemByName(canteenItemName);
        ModelAndView mv=new ModelAndView("message");
        if(findCtI==null){//菜品不存在的情况
            mv.addObject("tips","菜品不存在");
            mv.addObject("before","<a href='upload.jsp'>返回</a>");
            mv.addObject("next","<a href='canteenItem/addPage?name="+canteenItemName+"'>添加菜品</a>");
        }else {
            //菜品存在，上传图片
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res=sdf.format(new Date());
            String rootPath=request.getSession().getServletContext().getRealPath("WEB-INF/upload");
            String originalFileName=file.getOriginalFilename();
            String newFileName=res+originalFileName.substring(originalFileName.lastIndexOf("."));
            Calendar date= Calendar.getInstance();
            File dateDirs=new File(date.get(Calendar.YEAR)+File.separator+(date.get(Calendar.MONTH)+1));
            String newFilePath=rootPath+File.separator+dateDirs+File.separator+newFileName;
            File newFile=new File(newFilePath);
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            System.out.println(newFile);
            file.transferTo(newFile);
            Image image=new Image(newFilePath, findCtI.getCanteenItemId(), isMainImg);
            service.uploadImage(image);

            //添加成功后返回的信息
            String fileUrl=date.get(Calendar.YEAR)+"/"+(date.get(Calendar.MONTH)+1)+"/"+newFileName;
            mv.addObject("tips","添加成功["+fileUrl+"]");
            mv.addObject("before","<a href='upload.jsp'>返回</a>");
            mv.addObject("next","");
        }
        return mv;
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response,Integer imgId)throws Exception{
        Image find=service.findImgById(imgId);
        String path=find.getImgLoc();
        InputStream in=new BufferedInputStream(new FileInputStream(new File(path)));
        response.addHeader("Content-Disposition","attachment;filename="+"download"+path.substring(path.lastIndexOf(".")));
        response.setContentType("multipart/form-data");
        BufferedOutputStream out=new BufferedOutputStream(response.getOutputStream());
        byte[] buff =new byte[1024];
        int len=0;
        while((len=in.read(buff))!=-1){
            out.write(buff,0,len);
        }
        out.close();
    }

    @RequestMapping(value = "/getImage",method = RequestMethod.GET)
    public void getImage(HttpServletRequest request,HttpServletResponse response,Integer imgId)throws IOException{
        OutputStream out=null;
        InputStream in=null;
        Image find=service.findImgById(imgId);
        try{
            File file=new File(find.getImgLoc());
            String ext = file.getName().substring((file.getName().indexOf(".")));
            if(ext.equals("jpg")){
                response.setContentType("image/jpeg");
            }else if(ext.equals("JPG")){
                response.setContentType("image/jpeg");
            }else if(ext.equals("png")){
                response.setContentType("image/png");
            }else if(ext.equals("PNG")){
                response.setContentType("image/png");
            }
            in=new FileInputStream(file);
            out=new BufferedOutputStream(response.getOutputStream());
            byte[] buff =new byte[1024];
            int len=0;
            while((len=in.read(buff))!=-1){
                out.write(buff,0,len);
            }
            out.flush();
            out.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.close();
            }
            if(in!=null){
                in.close();
            }
        }
    }
    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAll() throws IOException{
        ObjectMapper om=new ObjectMapper();
        List<String> colNames=tableDao.selectColName("images");
        List<Image> all= service.FindAll();
        Map res=new HashMap();
        res.put("colname",colNames);
        res.put("content",all);
        String json=om.writeValueAsString(res);
        return json;
    }
    @RequestMapping(value = "/remove")
    public ModelAndView removeItem(Integer id){
        ModelAndView mv= new ModelAndView("message");
        String tip="删除失败";
        int num= service.RemoveItem(id);
        if(num!=0){
            tip="删除成功";
        }
        mv.addObject("tips",tip);
        mv.addObject("next","<a href='index.jsp'>返回</a>");
        return mv;
    }
}
