package com.qiashe.controller;

import com.qiashe.dao.UsersDao;
import com.qiashe.domain.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SystemController {
    private static String sysVersion="0.1.0";
    @Autowired
    private UsersDao usersDao;
    @RequestMapping("/version")
    @ResponseBody
    public String returnVersion(String version){
        if(version!=null){
            sysVersion=version;
        }
        return sysVersion;
    }
    @RequestMapping("/login")
    public String doLogin(ModelMap map,String userName, String pwd, HttpServletRequest request){
        String findpwd=null;
        User findUser=null;
        if(userName!=null)
            findUser= usersDao.selectByName(userName);
        if(findUser!=null){
            if(pwd!=null&&findUser.getPassword().equals(pwd)){
                HttpSession session=request.getSession();
                session.setAttribute("passport","qiashe");
                return "redirect:loginSuccess.jsp";
            }
        }
        return "redirect:loginFail.jsp";
    }
}
