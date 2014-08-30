package org.dispatcher.beans.passenger;

public class PassengerRegisterRequest {

	String routeNumber;
	String gcmId;
	String lat;
	String lon;
	int oldTimeInterval;
	int newTimeInterval;
	String newStartingPoint;
	String oldStartingPoint;
	
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
	public int getOldTimeInterval() {
		return oldTimeInterval;
	}
	public void setOldTimeInterval(int oldTimeInterval) {
		this.oldTimeInterval = oldTimeInterval;
	}
	public int getNewTimeInterval() {
		return newTimeInterval;
	}
	public void setNewTimeInterval(int newTimeInterval) {
		this.newTimeInterval = newTimeInterval;
	}
	public String getNewStartingPoint() {
		return newStartingPoint;
	}
	public void setNewStartingPoint(String newStartingPoint) {
		this.newStartingPoint = newStartingPoint;
	}
	public String getOldStartingPoint() {
		return oldStartingPoint;
	}
	public void setOldStartingPoint(String oldStartingPoint) {
		this.oldStartingPoint = oldStartingPoint;
	}
}
