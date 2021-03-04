package com.hamryt.helparty.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final String LOGIN_USER_EMAIL = "LOGIN_USER_EMAIL";

    private SessionUtil(){}

    public static void setLoginUserEmail(HttpSession session, String email){
        session.setAttribute(LOGIN_USER_EMAIL, email);
    }

}
