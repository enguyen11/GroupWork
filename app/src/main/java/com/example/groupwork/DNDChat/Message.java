package com.example.groupwork.DNDChat;

public class Message {
    public String sender;
    public String receiver;
    public String content;

    public Message(String sender, String receiver, String content){
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
