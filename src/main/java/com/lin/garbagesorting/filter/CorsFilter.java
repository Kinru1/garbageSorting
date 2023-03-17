//package com.lin.garbagesorting.filter;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.Filter;
//import java.util.logging.LogRecord;
//
//@Component
//public class CorsFilter  implements Filter {
//
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public boolean isLoggable(LogRecord record) {
//        return false;
//    }
//}
