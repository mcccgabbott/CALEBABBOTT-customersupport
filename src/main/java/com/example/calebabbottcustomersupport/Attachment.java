package com.example.calebabbottcustomersupport;

public class Attachment {
    private String name;
    private byte[] contents;

    public Attachment(String name, byte[] contents) {
        this.name = name;
        this.contents = contents;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
