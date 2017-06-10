package com.lcl.util;


import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by lcl on 2017/4/12.
 * web工具类
 */
public class WebUtils {
    /**
     * 通用的转跳方法
     * @param request
     * @param response
     * @param uri
     * @throws ServletException
     * @throws IOException
     */
    public static void goTo(HttpServletRequest request, HttpServletResponse response,Object uri) throws ServletException,IOException{
        if(uri instanceof RequestDispatcher){
            ((RequestDispatcher)uri).forward(request,response);
        }
        else if(uri instanceof  String){
            response.sendRedirect(request.getContextPath()+uri);
        }
    }

    public static <T> T copyTOBean(HttpServletRequest request,Class<T> tClass){
        try {
            T t=tClass.newInstance();
            Map map=request.getParameterMap();
            BeanUtils.populate(t,request.getParameterMap());
            return t;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
