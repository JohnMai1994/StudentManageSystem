package com.jiadong.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class RespUtils {
    public static void write(HttpServletResponse response, Object jsonObject) throws  Exception{
        //设置缓存区编码为UTF-8编码格式
        response.setCharacterEncoding("UTF-8");
        //在响应中主动告诉浏览器使用UTF-8编码格式来接收数据
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonObject.toString());
        out.flush();
        out.close();
    }


}
