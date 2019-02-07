package com.example.android.wiichat;

public class ChatUser
{
    private int chatPicture;
    private String chatName;
    private String chatMessage;

    public ChatUser(int chatPicture, String chatName, String chatMessage)
    {
        this.chatPicture = chatPicture;
        this.chatName = chatName;
        this.chatMessage = chatMessage;
    }

    public int getChatPicture(){
        return chatPicture;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public String getChatName(){
        return chatName;
    }
}
