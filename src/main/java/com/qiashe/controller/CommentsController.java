package com.qiashe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiashe.dao.TableDao;
import com.qiashe.domain.Comment;
import com.qiashe.service.CommentsService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
@RequestMapping("/comment")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private TableDao tableDao;
    @RequestMapping("/addComment")
    @ResponseBody
    public String doAddComment(String content,Integer userId,Integer canteenItemId,Float mark,Integer isLike){
        String tip="fail";
        if(userId==null||canteenItemId==null){
            return tip;
        }
        if(mark==null){
            mark=Float.valueOf(-1);
        }
        if(isLike==null){
            isLike=-1;
        }
        Comment comment=new Comment(userId,canteenItemId,content,mark,isLike);
        int num=commentsService.addComment(comment);
        if(num!=0){
            tip="success";
        }
        return tip;
    }
    @RequestMapping (value = "/findall",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doFind(Integer CIId) throws IOException {
        List<Comment> comments= commentsService.findCommentsByCIId(CIId);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(comments);
        return json;
    }
    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAll() throws IOException{
        ObjectMapper om=new ObjectMapper();
        List<String> colNames=tableDao.selectColName("comments");
        List<Comment> all= commentsService.findAll();
        Map res=new HashMap();
        res.put("colname",colNames);
        res.put("content",all);
        String json=om.writeValueAsString(res);
        return json;
    }
    @RequestMapping("/addPage")
    public ModelAndView toAddPage(){
        ModelAndView mv=new ModelAndView("addComment");
        return mv;
    }
    @RequestMapping(value = "/remove")
    public ModelAndView removeItem(Integer id){
        ModelAndView mv= new ModelAndView("message");
        String tip="删除失败";
        int num= commentsService.removeById(id);
        if(num!=0){
            tip="删除成功";
        }
        mv.addObject("tips",tip);
        mv.addObject("next","<a href='index.jsp'>返回</a>");
        return mv;
    }
    @RequestMapping(value = "/findByCIId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doFindByCIId(Integer CIId)throws IOException {
        List<Comment> comments= commentsService.findCommentsByCIId(CIId);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(comments);
        return json;
    }
    @RequestMapping(value = "/findUserComment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doFindUserComment(Integer userId,Integer CIId)throws IOException {
        Comment comment= commentsService.findUserComment(userId, CIId);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(comment);
        return json;
    }
}
