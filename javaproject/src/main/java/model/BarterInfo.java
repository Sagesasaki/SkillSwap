package model;

public class BarterInfo {
    private boolean isComplete;
    private String userName;
    private String otherUserName;
    private String userServiceName;
    private String otherUserServiceName;

    public BarterInfo() {
        isComplete = false;
        userName = "";
        otherUserName = "";
        userServiceName = "";
        otherUserServiceName = "";
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(String otherUserName) {
        this.otherUserName = otherUserName;
    }

    public String getUserServiceName() {
        return userServiceName;
    }

    public void setUserServiceName(String userServiceName) {
        this.userServiceName = userServiceName;
    }

    public String getOtherUserServiceName() {
        return otherUserServiceName;
    }

    public void setOtherUserServiceName(String otherUserServiceName) {
        this.otherUserServiceName = otherUserServiceName;
    }

    // Additional methods (e.g., toString) can be added as necessary
}
