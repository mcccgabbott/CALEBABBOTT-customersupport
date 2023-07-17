package com.example.calebabbottcustomersupport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.LinkedHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private Map<String, String> userCredentials;

    public void init() {
        // Initialize the user credentials map
        userCredentials = new HashMap<>();
        userCredentials.put("admin", "admin123");
        // Add more username-password combinations as needed
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check username and password against the user credentials map
        if (isValidUser(username, password)) {
            // Set user information in session
            request.getSession().setAttribute("username", username);

            // Redirect to the ticket list page
            response.sendRedirect(request.getContextPath() + "/TicketServlet?action=list");
        } else {
            // Show login page with error message
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isValidUser(String username, String password) {
        // Check if the provided username and password exist in the user credentials map
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}