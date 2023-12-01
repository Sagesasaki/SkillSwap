package model;

public class Service {
	private int service_id;
	private String name;
    private String description;
    private int user_id;
    private String user;
    public Service() {
    	service_id = 0;
    	name = null;
    	description = null;
    	user_id = 0;
	}
    public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUser_id() {
			return user_id;
	}
	public void setUser_id(int user_id) {
			this.user_id = user_id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
