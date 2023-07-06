package com.tht.api.app.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public  class MyAccessDeniedHandler extends AccessDeniedHandlerImpl {

    private HttpRequestEndpointChecker endpointChecker;

    public MyAccessDeniedHandler(HttpRequestEndpointChecker endpointChecker) {
        this.endpointChecker = endpointChecker;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (!endpointChecker.isEndpointExist(request)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        } else {
            super.handle(request, response, accessDeniedException);
        }
    }
}
