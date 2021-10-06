package com.qiashe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiashe.Util.LoginUtil;
import com.qiashe.dao.TableDao;
import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FacilityItem;
import com.qiashe.domain.Image;
import com.qiashe.service.CanteenItemsService;
import com.qiashe.service.CommentsService;
import com.qiashe.service.FacilityItemsService;
import com.qiashe.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/canteenItem")
public class CanteenItemsController {
    @Autowired
    private CanteenItemsService canteenItemsService;
    @Autowired
    private FacilityItemsService facilityItemsService;
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private TableDao tableDao;
    @Autowired
    private CommentsService commentsService;
    @RequestMapping(value = "/findLike",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String FindCanteenItemsByNameAlike(String Name){
        List<CanteenItem> canteenItems=canteenItemsService.FindCanteenItemByNameAlike(Name);
        ObjectMapper om=new ObjectMapper();
        String json=null;
        try {
            json=om.writeValueAsString(canteenItems);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    @RequestMapping(value = "/add")
    public ModelAndView addCanteenItem(String name, String info, Float price, Float spicyRate, Float sweetRate, Float mark, String canteenName, HttpServletRequest request){
        ModelAndView mv=new ModelAndView("message");
        HttpSession session= request.getSession(false);
        if(session==null){
            mv.addObject("tips","未登录");
            mv.addObject("before","<a href='login.jsp'>登录</a>");
            mv.addObject("next","");
            return mv;
        }
        FacilityItem findFyI= facilityItemsService.FindFacilityItemByName(canteenName);
        if(findFyI==null){
            mv.addObject("tips","设施不存在");
            mv.addObject("before","<a href='canteenItem/addPage'>返回</a>");
            mv.addObject("next","<a href='facilityItem/addPage?title="+canteenName+"'>添加设施</a>");
        }else{
            CanteenItem newCtI=new CanteenItem(name, findFyI.getFacilityItemId(), info,price,spicyRate,sweetRate,mark);
            canteenItemsService.AddCanteenItem(newCtI);
            mv.addObject("tips","添加成功");
            mv.addObject("before","<a href='canteenItem/addPage'>返回</a>");
            mv.addObject("next","");
        }
        return mv;
    }
    @RequestMapping(value = "/addPage")
    public ModelAndView toAddPage(String name,String info,Float price,Float spicyRate,Float sweetRate,Float mark,Integer canteenId,HttpServletRequest request){
        ModelAndView mv=new ModelAndView("addCanteenItem");
        if(!LoginUtil.validateLogon(request)){
            mv.setViewName("message");
            mv.addObject("tips","未登录");
            mv.addObject("before","<a href='login.jsp'>登录</a>");
            mv.addObject("next","");
            return mv;
        }
        if(name!=""&&name!=null){
            mv.addObject("name",name);
        }
        if(info!=""&&info!=null){
            mv.addObject("info",info);
            mv.addObject("price",price);
            mv.addObject("spicyRate",spicyRate);
            mv.addObject("sweetRate",sweetRate);
            mv.addObject("mark",mark);
            String canteenName=facilityItemsService.findItem(canteenId).getTitle();
            mv.addObject("canteenName",canteenName);
        }
        return mv;
    }
    @RequestMapping(value = "/findImg",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doFindImgForCanteenItem(String name,Integer canteenItemId) throws IOException {
        List<Image> images=null;
        if(canteenItemId!=null&&canteenItemId.toString()!="")
            images= imagesService.findImgByCIId(canteenItemId);
        else
            images=imagesService.findImgByCIName(name);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(images);
        return json;
    }
    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAll() throws IOException{
        ObjectMapper om=new ObjectMapper();
        List<String> colNames=tableDao.selectColName("canteenItems");
        List<CanteenItem> all= canteenItemsService.FindAll();
        Map res=new HashMap();
        res.put("colname",colNames);
        res.put("content",all);
        String json=om.writeValueAsString(res);
        return json;
    }
    @RequestMapping(value = "/findall",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findall() throws IOException{
        ObjectMapper om=new ObjectMapper();
        List<CanteenItem> all= canteenItemsService.FindAll();
        String json=om.writeValueAsString(all);
        return json;
    }
    @RequestMapping(value = "/remove")
    public ModelAndView removeItem(Integer id,HttpServletRequest request){
        ModelAndView mv= new ModelAndView("message");
        if(!LoginUtil.validateLogon(request)){
            mv.setViewName("message");
            mv.addObject("tips","未登录");
            mv.addObject("before","<a href='login.jsp'>登录</a>");
            mv.addObject("next","");
            return mv;
        }
        String tip="删除失败";
        int num= canteenItemsService.RemoveItem(id);
        if(num!=0){
            tip="删除成功";
        }
        mv.addObject("tips",tip);
//        mv.addObject("next","<a href='index.jsp'>返回</a>");
        return mv;
    }
    @RequestMapping(value = "/findByFI",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doFindByFI(Integer facilityItemId) throws IOException{
        List<CanteenItem> canteenItems= canteenItemsService.FindByFI(facilityItemId);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(canteenItems);
        return json;
    }
    @RequestMapping(value = "/getMLC",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doGetMLC(Integer CIId) throws IOException{
        Map<String,String> map=commentsService.findMLC(CIId);
        ObjectMapper om =new ObjectMapper();
        String json=om.writeValueAsString(map);
        return json;
    }
}
