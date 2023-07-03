package com.example.calebabbottcustomersupport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet (name ="TicketServlet", value="/TicketServlet")
public class TicketServlet extends HttpServlet {
    private Map<Integer, Ticket> ticketMap = new HashMap<>();
    private AtomicInteger ticketIdGenerator = new AtomicInteger(1);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            listTickets(request, response);
        } else {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2 && pathParts[1].equals("form")) {
                showTicketForm(request, response);
            } else if (pathParts.length == 2 && pathParts[1].equals("view")) {
                String ticketIdParam = request.getParameter("id");
                if (ticketIdParam != null) {
                    int ticketId = Integer.parseInt(ticketIdParam);
                    viewTicket(request, response, ticketId);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "com.example.calebabbottcustomersupport.Ticket ID parameter is missing");
                }
            } else if (pathParts.length == 2 && pathParts[1].equals("download")) {
                String ticketIdParam = request.getParameter("id");
                String attachmentIndexParam = request.getParameter("attachmentIndex");
                if (ticketIdParam != null && attachmentIndexParam != null) {
                    int ticketId = Integer.parseInt(ticketIdParam);
                    int attachmentIndex = Integer.parseInt(attachmentIndexParam);
                    downloadAttachment(request, response, ticketId, attachmentIndex);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "com.example.calebabbottcustomersupport.Ticket ID and com.example.calebabbottcustomersupport.attachment index parameters are missing");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/create")) {
            createTicket(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL");
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("tickets", ticketMap.values());
        request.getRequestDispatcher("/ticket-list.jsp").forward(request, response);
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response, int ticketId)
            throws ServletException, IOException {
        Ticket ticket = getTicket(ticketId);
        if (ticket != null) {
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("/ticket-view.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "com.example.calebabbottcustomersupport.Ticket not found");
        }
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        if (customerName != null && subject != null && body != null) {
            Ticket ticket = new Ticket(ticketIdGenerator.getAndIncrement(), customerName, subject, body);
            ticketMap.put(ticket.getId(), ticket);
            response.sendRedirect(request.getContextPath() + "/tickets");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ticket information");
        }
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response, int ticketId,
                                    int attachmentIndex) throws ServletException, IOException {
        Ticket ticket = getTicket(ticketId);
        if (ticket != null) {
            Map<Integer, attachment> attachments = ticket.getAllAttachments();
            attachment attachment = attachments.get(attachmentIndex);
            if (attachment != null) {
                response.setHeader("Content-Disposition", "com.example.calebabbottcustomersupport.attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType("application/octet-stream");
                try (OutputStream outputStream = response.getOutputStream()) {
                    outputStream.write(attachment.getContents());
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Attachment not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "com.example.calebabbottcustomersupport.Ticket not found");
        }
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/ticket-form.jsp").forward(request, response);
    }

    private Ticket getTicket(int ticketId) {
        return ticketMap.get(ticketId);
    }

    private void processAttachment(Part part, Ticket ticket) throws IOException {
        String fileName = part.getSubmittedFileName();
        InputStream inputStream = part.getInputStream();
        byte[] contents = inputStream.readAllBytes();
        attachment attachment = new attachment(fileName, contents);
        ticket.addAttachment(attachment);
    }
}