package com.example.chatSocket.components;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import java.io.IOException;

@Component
public class SimpleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Remote Host:"+servletRequest.getContentLength());
        System.out.println("Remote Address:"+servletRequest.getContentType());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
