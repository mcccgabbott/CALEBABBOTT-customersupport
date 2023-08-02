package com.example.calebabbottcustomersupport;

import java.util.Arrays;

public class Attachment {
    private String name;
    private byte[] content;

    public Attachment(String name, byte[] contents) {
        this.name = name;
        this.content = contents;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] contents) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", contents=" + Arrays.toString(content) +
                '}';
    }
}
