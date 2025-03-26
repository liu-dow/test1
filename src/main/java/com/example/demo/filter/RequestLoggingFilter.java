package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Filter to log all incoming HTTP requests and their responses.
 * Prints method, URI, parameters, headers, and response status.
 */
@Component
@Order(1) // High priority to ensure this filter runs early
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("RequestLoggingFilter initialized");
    }

    // List of static resource extensions to ignore
    private static final String[] IGNORED_EXTENSIONS = {
        ".css", ".js", ".png", ".jpg", ".jpeg", ".gif", ".ico", ".svg", ".woff", ".woff2", ".ttf", ".eot","webp","avif"
    };
    
    /**
     * Check if the request is for a static resource that should be ignored in logging
     */
    private boolean isStaticResource(String uri) {
        if (uri == null) return false;
        
        for (String extension : IGNORED_EXTENSIONS) {
            if (uri.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if the request is an API request
     */
    private boolean isApiRequest(String uri) {
        return uri != null && uri.startsWith("/api/");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String uri = httpRequest.getRequestURI();
        
        // Skip logging for static resources
        if (isStaticResource(uri)) {
            chain.doFilter(request, response);
            return;
        }
        
        // Capture response status
        StatusResponseWrapper responseWrapper = new StatusResponseWrapper(httpResponse);
        
        long startTime = System.currentTimeMillis();

        // Log request details - only for webpage and API requests
        if (isApiRequest(uri)) {
            // API request logging
            logger.info("API Request: {} {} | Query: {} ",
                    httpRequest.getMethod(),
                    uri,
                    httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "");
        } else {
            // Webpage request logging
            logger.info("Page Request: {} {} | Query: {}",
                    httpRequest.getMethod(),
                    uri,
                    httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "");
        }
        
        // Continue with the filter chain
        chain.doFilter(request, responseWrapper);
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Log response details
        if (isApiRequest(uri)) {
            logger.info("API Response: {} {} | Status: {} | Time: {} ms",
                    httpRequest.getMethod(),
                    uri, 
                    responseWrapper.getStatus(), 
                    duration);
        } else {
            logger.info("Page Response: {} {} | Status: {} | Time: {} ms",
                    httpRequest.getMethod(),
                    uri, 
                    responseWrapper.getStatus(), 
                    duration);
        }
    }

    @Override
    public void destroy() {
        logger.info("RequestLoggingFilter destroyed");
    }
    
    /**
     * Wrapper class to capture the status code of the response
     */
    private static class StatusResponseWrapper extends javax.servlet.http.HttpServletResponseWrapper {
        
        private int httpStatus;
        
        public StatusResponseWrapper(HttpServletResponse response) {
            super(response);
        }
        
        @Override
        public void setStatus(int sc) {
            super.setStatus(sc);
            this.httpStatus = sc;
        }
        
        @Override
        public void sendError(int sc) throws IOException {
            super.sendError(sc);
            this.httpStatus = sc;
        }
        
        @Override
        public void sendError(int sc, String msg) throws IOException {
            super.sendError(sc, msg);
            this.httpStatus = sc;
        }
        
        @Override
        public void setStatus(int sc, String sm) {
            super.setStatus(sc, sm);
            this.httpStatus = sc;
        }
        
        public int getStatus() {
            return httpStatus;
        }
    }
}