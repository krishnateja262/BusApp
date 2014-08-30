package org.dispatcher.beans;

public class UserBean {

	String username;
	String password;
	String type;
	
	public UserBean(){}
	
	public UserBean(String username, String password){
		this.username = username;
		this.password = password;
		this.type = "driver";
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return username+":"+password+":"+type;
	}
}
