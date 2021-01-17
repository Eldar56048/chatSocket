package com.example.chatSocket.models;

public class Message {
    private String name;
    private String user;

    public Message() {
    }

    public Message(String user) {
        this.user = user;
    }
    public Message(String user,String name) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }
}
