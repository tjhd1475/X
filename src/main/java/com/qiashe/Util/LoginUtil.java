package com.qiashe.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {
    public static boolean validateLogon(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        String passport=(String) session.getAttribute("passport");
        if(session==null||passport==null||!passport.equals("qiashe")){
            return false;
        }
        return true;
    }
}
