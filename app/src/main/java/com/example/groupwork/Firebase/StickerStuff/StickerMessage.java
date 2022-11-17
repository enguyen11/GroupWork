package com.example.groupwork.Firebase.StickerStuff;

public class StickerMessage {
    public String sender;
    public String receiver;
    public String content;

    public StickerMessage(String sender, String receiver, String content){
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
