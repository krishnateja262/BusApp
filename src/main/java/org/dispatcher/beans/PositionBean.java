package org.dispatcher.beans;

public class PositionBean {

	String lat;
	String lon;
	boolean sendNotification;
	
	public PositionBean(){}
	
	public PositionBean(String lat, String lon){
		this.lat = lat;
		this.lon = lon;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}

	public boolean isSendNotification() {
		return sendNotification;
	}

	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}
	
}
