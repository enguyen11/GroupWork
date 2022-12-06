package com.example.groupwork.Chat;

public class ComposeMessage {

    private String userEmail;
    private String message;

    public ComposeMessage(){

    }
    public ComposeMessage(String userEmail, String message) {
        this.userEmail = userEmail;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "userEmail: " + this.userEmail + "\nMessage: " + this.message;
    }


}
