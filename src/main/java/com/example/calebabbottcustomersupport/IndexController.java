package com.example.calebabbottcustomersupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public View index() {
        return new RedirectView("/tickets/list", true, false);
    }
}


