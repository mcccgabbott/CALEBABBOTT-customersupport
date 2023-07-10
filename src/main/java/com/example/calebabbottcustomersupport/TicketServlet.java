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



@WebServlet(name = "TicketServlet", value = "/TicketServlet")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {
    private Map<Integer, Ticket> ticketMap = new HashMap<>();
    private AtomicInteger ticketIdGenerator = new AtomicInteger(1);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTickets(request, response);
                break;
            case "view":
                viewTicket(request, response);
                break;
            case "download":
                downloadAttachment(request, response);
                break;
            case "form":
                showTicketForm(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                createTicket(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/TicketServlet?action=list");
                break;
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Integer, Ticket> tickets = ticketMap;
        Map<Integer, Boolean> hasAttachmentsMap = new LinkedHashMap<>();

        for (Ticket ticket : tickets.values()) {
            boolean hasAttachments = !ticket.getAllAttachments().isEmpty();
            hasAttachmentsMap.put(ticket.getId(), hasAttachments);
        }

        request.setAttribute("tickets", tickets);
        request.setAttribute("hasAttachmentsMap", hasAttachmentsMap);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ticketIdParam = request.getParameter("id");
        if (ticketIdParam != null) {
            int ticketId = Integer.parseInt(ticketIdParam);
            Ticket ticket = getTicket(ticketId);
            if (ticket != null) {
                request.setAttribute("ticket", ticket);
                request.getRequestDispatcher("/ticketview.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket ID parameter is missing");
        }
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        if (customerName != null && subject != null && body != null) {
            Ticket ticket = new Ticket(ticketIdGenerator.getAndIncrement(), customerName, subject, body);

            // Handle file upload
            Part filePart = request.getPart("attachment");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                InputStream inputStream = filePart.getInputStream();
                byte[] contents = inputStream.readAllBytes();
                Attachment attachment = new Attachment(fileName, contents);
                ticket.addAttachment(attachment);
            }

            ticketMap.put(ticket.getId(), ticket);

            // Set a success message in the request attribute
            request.setAttribute("message", "Ticket created successfully");

            // Redirect to the ticket list page
            response.sendRedirect(request.getContextPath() + "/TicketServlet?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ticket information");
        }
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ticketIdParam = request.getParameter("id");
        String attachmentIndexParam = request.getParameter("attachmentIndex");
        if (ticketIdParam != null && attachmentIndexParam != null) {
            int ticketId = Integer.parseInt(ticketIdParam);
            int attachmentIndex = Integer.parseInt(attachmentIndexParam);
            Ticket ticket = getTicket(ticketId);
            if (ticket != null) {
                Map<Integer, Attachment> attachments = ticket.getAllAttachments();
                Attachment attachment = attachments.get(attachmentIndex);
                if (attachment != null) {
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                    response.setContentType("application/octet-stream");
                    try (OutputStream outputStream = response.getOutputStream()) {
                        outputStream.write(attachment.getContents());
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Attachment not found");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket ID and Attachment index parameters are missing");
        }
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/ticketform.jsp").forward(request, response);
    }

    private Ticket getTicket(int ticketId) {
        return ticketMap.get(ticketId);
    }

    private void processAttachment(Part part, Ticket ticket) throws IOException {
        String fileName = part.getSubmittedFileName();
        InputStream inputStream = part.getInputStream();
        byte[] contents = inputStream.readAllBytes();
        Attachment attachment = new Attachment(fileName, contents);
        ticket.addAttachment(attachment);
    }

    public void destroy() {
    }
}
