package com.example.calebabbottcustomersupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    private final TicketService ticketService;

    @Autowired
    public IndexController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping("/")
    public String showTicketList(Model model) {
        Map<Integer, Ticket> tickets = ticketService.getAllTickets();
        Map<Integer, Boolean> hasAttachmentsMap = ticketService.getAttachmentsStatusForAllTickets();

        model.addAttribute("tickets", tickets);
        model.addAttribute("hasAttachmentsMap", hasAttachmentsMap);

        return "/ticket/list";
    }
}


