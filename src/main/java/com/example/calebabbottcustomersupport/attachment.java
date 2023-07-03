package com.example.calebabbottcustomersupport;

public class attachment {
    private String name;
    private byte[] contents;

    public attachment(String fileName, byte[] contents) {
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
