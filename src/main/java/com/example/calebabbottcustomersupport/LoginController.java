package com.example.calebabbottcustomersupport;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // Map the login page to the /login URL
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm() {
        return new ModelAndView("login");
    }

    // Map the login form submission to the /login URL with POST method
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView processLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check username and password
        if (isValidUser(username, password)) {
            // Set user information in session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to the homepage
            return new ModelAndView("redirect:/tickets/list");
        } else {
            // Show login page with error message
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }


    private boolean isValidUser(String username, String password) {

        return username.equals("admin") && password.equals("admin123");
    }
}

/*import java.io.IOException;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                processLogin(request, response);
                break;
            case "signup":
                processSignup(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
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

    private void processSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username already exists in the user credentials map
        if (userCredentials.containsKey(username)) {
            // Show signup page with error message
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Add the username and password to the user credentials map
        userCredentials.put(username, password);

        // Set user information in session
        request.getSession().setAttribute("username", username);

        // Redirect to the ticket list page
        response.sendRedirect(request.getContextPath() + "/TicketServlet?action=list");
    }

    private boolean isValidUser(String username, String password) {
        // Check if the provided username and password exist in the user credentials map
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}*/