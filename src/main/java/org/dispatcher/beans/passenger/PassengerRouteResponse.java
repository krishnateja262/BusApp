package org.dispatcher.beans.passenger;

import java.util.HashMap;

public class PassengerRouteResponse {

	String status;
	HashMap<String, StartEndTimeBean> hashMap;
	
	public PassengerRouteResponse(HashMap<String, StartEndTimeBean> hashMap){
		this.hashMap = new HashMap<String, StartEndTimeBean>(hashMap);
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HashMap<String, StartEndTimeBean> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<String, StartEndTimeBean> hashMap) {
		this.hashMap = hashMap;
	}
}
