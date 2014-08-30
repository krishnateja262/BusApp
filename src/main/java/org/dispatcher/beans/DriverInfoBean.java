package org.dispatcher.beans;

public class DriverInfoBean {
	
	String routeNumber;
	String gcmId;
	String lat;
	String lon;
	boolean sendNotification;
	
	public String getRouteNumber() {
		return routeNumber;
	}
	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public boolean isSendNotification() {
		return sendNotification;
	}
	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
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
}
