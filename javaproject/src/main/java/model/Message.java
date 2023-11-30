package model;

public class Message {
    private boolean is_complete;
    private int service_id_1;
    private int service_id_2;
    private int user_id_1;
    private int user_id_2;

    public Message() {
        is_complete = false;
        service_id_1 = 0;
        service_id_2 = 0;
        user_id_1 = 0;
        user_id_2 = 0;
     //   messages = new Array(MessageObject(0, ""));
    }

  //  private MessageObject[] messages = new MessageObject[];

    public void setIs_complete(boolean is_complete) {
        this.is_complete = is_complete;
    }

    public boolean getIs_complete() {
        return is_complete;
    }

    public int getService_id1() {
        return service_id_1;
    }

    public void setService_id1(int service_id_1) {
        this.service_id_1 = service_id_1;
    }

    public int getService_id2() {
        return service_id_2;
    }

    public void setService_id2(int service_id_2) {
        this.service_id_2 = service_id_2;
    }

    public int getUser_id1() {
        return user_id_1;
    }

    public void setUser_id1(int user_id) {
        this.user_id_1 = user_id;
    }

    public int getUser_id2() {
        return user_id_2;
    }

    public void setUser_id2(int user_id) {
        this.user_id_2 = user_id;
    }

//    public MessageObject[] getMessages() {
//        return this.messages;
//    }
//
//    public void addMessage(int user_id, String text) {
//        MessageObject message = new MessageObject(user_id, text);
//        this.messages.push(message);
//    }
//    
    public class MessageObject {
        private int user_id = 0;
        private String text = "";

        public MessageObject(int userId, String messageText) {
            user_id = userId;
            text = messageText;
        }
    }
}

