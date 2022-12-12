package com.example.groupwork.DNDChat;

public class ClickableChat {
    String chatName;

    public ClickableChat(String chatName){
        this.chatName = chatName;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    @Override
    public String toString() {
        return chatName;
    }
}
