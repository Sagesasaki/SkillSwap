package model;

//import java.util.ArrayList;

public class User {
	private int user_id;
	private String username;
    private String password;
    private String name;
    public User() {
		user_id = 0;
		username = null;
		password = null;
		name = null;
	}
    public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String email) {
		this.name = email;
	}
}
