package com.example.calebabbottcustomersupport;

import com.example.calebabbottcustomersupport.attachment;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private int id;
    private String customerName;
    private String subject;
    private String body;
    private Map<Integer, attachment> attachments;

    public Ticket(int id, String customerName, String subject, String body) {
        this.id = id;
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void addAttachment(attachment attachment) {
        attachments.put(attachments.size() + 1, attachment);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addAttachment(int index, attachment attachment) {
        attachments.put(index, attachment);
    }

    public int getNumberOfAttachments() {
        return attachments.size();
    }

    public attachment getAttachment(int index) {
        return attachments.get(index);
    }

    public Map<Integer, attachment> getAllAttachments() {
        return attachments;
    }
}