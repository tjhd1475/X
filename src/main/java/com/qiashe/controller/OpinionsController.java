package com.qiashe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiashe.dao.TableDao;
import com.qiashe.domain.Image;
import com.qiashe.domain.Opinion;
import com.qiashe.service.OpinionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/opinion")
public class OpinionsController {
    @Autowired
    private OpinionsService opinionsService;
    @Autowired
    private TableDao tableDao;
    @RequestMapping("/submit")
    @ResponseBody
    public String dosubmit(String content,Integer type){
        Opinion opinion=new Opinion(content,null,type,0);
        String res="fail";
        int num= opinionsService.addOpinion(opinion);
        if(num!=0){
            res="success";
        }
        return res;
    }
    @RequestMapping("/addPage")
    public ModelAndView toAddPage(){
        ModelAndView mv=new ModelAndView("addOpinion");
        return mv;
    }
    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAll() throws IOException {
        ObjectMapper om=new ObjectMapper();
        List<String> colNames=tableDao.selectColName("opinions");
        List<Opinion> all= opinionsService.findAllOpinions();
        Map res=new HashMap();
        res.put("colname",colNames);
        res.put("content",all);
        String json=om.writeValueAsString(res);
        return json;
    }
    @RequestMapping("/detail")
    public ModelAndView doDetail(Integer id){
        ModelAndView mv=new ModelAndView("opinionDetail");
        Opinion opinion= opinionsService.findOpinionById(id);
        mv.addObject("opinion",opinion);
        return mv;
    }
    @RequestMapping("/solution")
    public ModelAndView doSolution(String solution,Integer result,Integer id){
        ModelAndView mv=new ModelAndView("message");
        mv.addObject("tips","成功");
        mv.addObject("next","<a href='index.jsp'>返回</a>");
        Opinion opinion=new Opinion(id,null,solution,null,result);
        opinionsService.solveOpinion(opinion);
        return mv;
    }
}
