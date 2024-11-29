package com.example.filter;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/*")
public class JwtFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(JwtFilter.class);

    // 请确保这个密钥与生成JWT时使用的密钥相同
    private static final String SECRET_KEY = "eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9.eyJzdWIiOiAidXNlcjEiLCAibmFtZSI6ICJKb2huIERvZSJ9.b99b7b6abf2da6d8468f043d474d56e77f8cfa7d7cc2ef57d3304b0131f575dd";

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes()) // 使用字节数组作为密钥
                        .parseClaimsJws(token)
                        .getBody();

                // 将解析的claims放到请求属性中，以便后续使用
                httpRequest.setAttribute("claims", claims);
                chain.doFilter(request, response);
            } catch (ExpiredJwtException e) {
                logger.error("Token expired", e);
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("{\"error\": \"Token expired\"}");
            } catch (SignatureException e) {
                logger.error("Invalid token", e);
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("{\"error\": \"Invalid token\"}");
            } catch (Exception e) {
                logger.error("Token processing error", e);
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("{\"error\": \"Token processing error\"}");
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("{\"error\": \"Missing or invalid Authorization header\"}");
        }
    }

    @Override
    public void destroy() {}
}
