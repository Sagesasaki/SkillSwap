package model;

import java.time.LocalDateTime;

public class ChatRoomMessage {
    private int message_id;
    private int sender_id;
    private String message_text;
    private LocalDateTime timestamp;

    public ChatRoomMessage() {
        // Initialize with default values
        this.message_id = 0;
        this.sender_id = 0;
        this.message_text = "";
        this.timestamp = LocalDateTime.now(); // Initialize with the current date and time
    }

    // Getters and Setters for each field
    public int getMessageId() {
        return message_id;
    }

    public void setMessageId(int message_id) {
        this.message_id = message_id;
    }

    public int getSenderId() {
        return sender_id;
    }

    public void setSenderId(int sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessageText() {
        return message_text;
    }

    public void setMessageText(String message_text) {
        this.message_text = message_text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
