package model;

public class Request {
	int user_id;
	int service_id;
	String request_text;
	public Request(){
		user_id = 0;
		service_id = 0;
		request_text = null;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public String getRequest_text() {
		return request_text;
	}
	public void setReview(String request_text) {
		this.request_text = request_text;
	}
}
