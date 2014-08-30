package org.dispatcher.beans.passenger;

public class StartEndTimeBean {

	String routeUrl;
	String startingPointTime;
	String endingPointTime;
	
	public String getStartingPointTime() {
		return startingPointTime;
	}
	public void setStartingPointTime(String startingPointTime) {
		this.startingPointTime = startingPointTime;
	}
	public String getEndingPointTime() {
		return endingPointTime;
	}
	public void setEndingPointTime(String endingPointTime) {
		this.endingPointTime = endingPointTime;
	}
	public String getRouteUrl() {
		return routeUrl;
	}
	public void setRouteUrl(String routeUrl) {
		this.routeUrl = routeUrl;
	}	
}
