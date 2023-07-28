package com.example.calebabbottcustomersupport;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TicketService {

    private Map<Integer, Ticket> ticketMap = new HashMap<>();
    private AtomicInteger ticketIdGenerator = new AtomicInteger(1);

    public Ticket createTicket(String customerName, String subject, String body, MultipartFile attachment) {
        Ticket newTicket = new Ticket(ticketIdGenerator.getAndIncrement(), customerName, subject, body);


        if (attachment != null && !attachment.isEmpty()) {
            String fileName = attachment.getOriginalFilename();
            byte[] contents;
            try {
                contents = attachment.getBytes();
            } catch (IOException e) {

                contents = new byte[0];
            }
            Attachment ticketAttachment = new Attachment(fileName, contents);
            newTicket.addAttachment(ticketAttachment);
        }

        ticketMap.put(newTicket.getId(), newTicket);
        return newTicket;
    }


    public Ticket getTicketById(int ticketId) {
        return ticketMap.get(ticketId);
    }


    public Map<Integer, Ticket> getAllTickets() {
        return ticketMap;
    }


    public void deleteTicket(int ticketId) {
        ticketMap.remove(ticketId);
    }
}
