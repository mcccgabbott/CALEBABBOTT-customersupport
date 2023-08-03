package com.example.calebabbottcustomersupport;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.List;

@Controller
@RequestMapping("/sessions")
public class SessionListenerController implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionRegistry.removeSession(se.getSession());
    }

    @GetMapping
    public ModelAndView listActiveSessions() {
        List<HttpSession> activeSessions = SessionRegistry.getActiveSessions();
        ModelAndView modelAndView = new ModelAndView("sessions");
        modelAndView.addObject("activeSessions", activeSessions);
        return modelAndView;
    }
}
