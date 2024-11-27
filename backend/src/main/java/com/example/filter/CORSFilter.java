package com.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置跨域请求
        System.out.println("Filter has been executed...");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //允许跨域主机地址
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许跨域方法
        response.setHeader("Access-Control-Allow-Methods", "POST, DELETE,PUT,GET,OPTIONS");
        //缓存时间
        response.setHeader("Access-Control-Max-Age", "3600");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //允许放行
        chain.doFilter(request, response);
    }

}
