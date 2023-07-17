package com.example.calebabbottcustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.IOException;

@WebServlet(name = "SessionListenerServlet", value = "/SessionListenerServlet")
public class SessionListenerServlet extends HttpServlet implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SessionRegistry.addSession(se.getSession());
}

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionRegistry.removeSession(se.getSession());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("sessions.jsp");
    }
}