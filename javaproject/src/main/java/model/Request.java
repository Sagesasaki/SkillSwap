package model;

public class Request {
    private int user_id;
    private int offered_service_id;
    private int requested_service_id;
    private String request_text;
    private String status;
    private int request_id;
    private String requester;

    public Request() {
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOffered_service_id() {
        return offered_service_id;
    }

    public void setOffered_service_id(int offered_service_id) {
        this.offered_service_id = offered_service_id;
    }

    public int getRequested_service_id() {
        return requested_service_id;
    }

    public void setRequested_service_id(int requested_service_id) {
        this.requested_service_id = requested_service_id;
    }

    public String getRequest_text() {
        return request_text;
    }

    public void setRequest_text(String request_text) {
        this.request_text = request_text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}
}
