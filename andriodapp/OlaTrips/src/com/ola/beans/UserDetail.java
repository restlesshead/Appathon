package com.ola.beans;

public class UserDetail {

	private String userName;
	private String userId;
	private double userLatitude;
	private double userLogitude;
	private String userAcceptedInvite;
	private String numberOfTravellers;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(double userLatitude) {
		this.userLatitude = userLatitude;
	}

	public double getUserLogitude() {
		return userLogitude;
	}

	public void setUserLogitude(double userLogitude) {
		this.userLogitude = userLogitude;
	}

	public String getUserAcceptedInvite() {
		return userAcceptedInvite;
	}

	public void setUserAcceptedInvite(String userAcceptedInvite) {
		this.userAcceptedInvite = userAcceptedInvite;
	}

	public String getNumberOfTravellers() {
		return numberOfTravellers;
	}

	public void setNumberOfTravellers(String numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}

}
