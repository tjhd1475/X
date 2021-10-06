package com.qiashe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiashe.Util.LoginUtil;
import com.qiashe.dao.TableDao;
import com.qiashe.domain.CanteenItem;
import com.qiashe.domain.FI_Avg_Result;
import com.qiashe.domain.FacilityItem;
import com.qiashe.service.FacilityItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/facilityItem")
public class FacilityItemsController {
    @Autowired
    private FacilityItemsService facilityItemsService;
    @Autowired
    private TableDao tableDao;

    @RequestMapping(value = "/findLike", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String FindCanteenItemsByNameAlike(String Name) {
        List<FacilityItem> facilityItems = facilityItemsService.FindFacilityItemByNameAlike(Name);
        ObjectMapper om = new ObjectMapper();
        String json = null;
        try {
            json = om.writeValueAsString(facilityItems);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(value = "/add")
    public ModelAndView addFacilityItem(String title, String info, Double positionx, Double positiony, Float price, String time, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("message");
        if(!LoginUtil.validateLogon(request)){
            mv.setViewName("message");
            mv.addObject("tips","未登录");
            mv.addObject("before","<a href='login.jsp'>登录</a>");
            mv.addObject("next","");
            return mv;
        }
        if (1 == 0) {
            mv.addObject("tips", "");
            mv.addObject("before", "<a href='facilityItem/addPage'>返回</a>");
            mv.addObject("next", "?");
        } else {
            FacilityItem newFtI = new FacilityItem(title, info, positionx, positiony, price, time);
            facilityItemsService.AddFacilityItem(newFtI);
            mv.addObject("tips", "添加成功");
            mv.addObject("before", "<a href='facilityItem/addPage'>返回</a>");
            mv.addObject("next", "");
        }
        return mv;
    }

    @RequestMapping(value = "/addPage")
    public ModelAndView toAddPage(String title, String info, Double positionx, Double positiony, Float price, String time,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("addFacilityItem");
        if(!LoginUtil.validateLogon(request)){
            mv.setViewName("message");
            mv.addObject("tips","未登录");
            mv.addObject("before","<a href='login.jsp'>登录</a>");
            mv.addObject("next","");
            return mv;
        }
        if(title!=""&&title!=null)
            mv.addObject("title",title);
        if(info!=""&&info!=null){
            mv.addObject("info",info);
            mv.addObject("positionx",positionx);
            mv.addObject("positiony",positiony);
            mv.addObject("price",price);
            mv.addObject("time",time);
        }
        return mv;
    }

    @RequestMapping(value = "/find", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doFind(String title) throws IOException {
        FacilityItem facilityItem = facilityItemsService.FindFacilityItemByName(title);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(facilityItem);
        return json;
    }

    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAll() throws IOException{
        ObjectMapper om=new ObjectMapper();
        List<String> colNames=tableDao.selectColName("facilityItems");
        List<FacilityItem> all= facilityItemsService.FindAll();
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
        List<FacilityItem> all= facilityItemsService.FindAll();
        String json=om.writeValueAsString(all);
        return json;
    }
    @RequestMapping(value = "/remove")
    public ModelAndView removeItem(Integer id,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("message");
        if(!LoginUtil.validateLogon(request)){
            mv.setViewName("message");
            mv.addObject("tips","未登录");
            mv.addObject("before","<a href='login.jsp'>登录</a>");
            mv.addObject("next","");
            return mv;
        }
        String tip = "删除失败";
        int num = facilityItemsService.RemoveItem(id);
        if (num != 0) {
            tip = "删除成功";
        }
        mv.addObject("tips", tip);
//        mv.addObject("next", "<a href='index.jsp'>返回</a>");
        return mv;
    }
    @RequestMapping(value = "/getAvgPriceAndMark",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doGetAvg(Integer FIId) throws IOException{
        Map<String,Float> map= facilityItemsService.findAvgPriceAndMark(FIId);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(map);
        return json;
    }
    @RequestMapping(value = "/getAllAvg",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doGetAllAVG()throws IOException{
        List<FI_Avg_Result> fi= facilityItemsService.findAllAvg();
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(fi);
        return json;
    }
}