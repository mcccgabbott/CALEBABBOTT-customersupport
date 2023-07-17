package com.example.calebabbottcustomersupport;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String loginURI = request.getContextPath() + "/LoginServlet";

        HttpSession session = request.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute("username") != null;
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);

        if (isLoggedIn || isLoginRequest) {
            // User is logged in or accessing the login page, so continue with the request
            filterChain.doFilter(request, response);
        } else {
            // User is not logged in, redirect to the login page
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}