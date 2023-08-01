package com.example.calebabbottcustomersupport;

import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private int id;
    private String customerName;
    private String subject;
    private String body;
    private MultipartFile attachment;
    private Map<Integer, Attachment> attachments;

    // Add a no-argument constructor
    public Ticket() {
        this.attachments = new HashMap<>();
    }

    public Ticket(int id, String customerName, String subject, String body) {
        this.id = id;
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new HashMap<>();
    }

    public Ticket(String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAttachment(Attachment attachment) {
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

    public void addAttachment(int index, Attachment attachment) {
        attachments.put(index, attachment);
    }

    public int getNumberOfAttachments() {
        return attachments.size();
    }

    public Attachment getAttachment(int index) {
        return attachments.get(index);
    }

    public Map<Integer, Attachment> getAllAttachments() {
        return attachments;
    }

    // Add the getter and setter for the attachment field
    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }

    // Add toString() method for debugging and logging purposes
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}

