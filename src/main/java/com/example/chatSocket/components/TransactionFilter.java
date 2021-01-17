package com.example.chatSocket.components;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class TransactionFilter implements Filter {

    @Override
    public void doFilter(
    ServletRequest request,
    ServletResponse response,
    FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Starting a transaction for req : "+req.getRequestURI());
        chain.doFilter(request, response);
        System.out.println("Committing a transaction for req : "+req.getRequestURI());
    }
}

