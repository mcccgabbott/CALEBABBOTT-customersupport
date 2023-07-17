package com.example.calebabbottcustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {

    private Map<String, String> userMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the signup page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the input and check if the username is already taken
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            if (!userMap.containsKey(username)) {
                // Store the new user's information in the map
                userMap.put(username, password);

                // Redirect to the login page with a success message
                response.sendRedirect(request.getContextPath() + "/LoginServlet?signupSuccess=true");
                return;
            } else {
                // User already exists
                request.setAttribute("error", "Username already taken");
            }
        } else {
            // Invalid input
            request.setAttribute("error", "Invalid username or password");
        }

        // Show the signup page with an error message
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
