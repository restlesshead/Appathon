package com.ola.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripDetails {

	private String tripId;
	private String tripName;
	private String tripDestination;
	private String tripIntialtor;
	private String tripPlannedAttendies;
	private String tripAcceptedAttendies;
	private String tripNumberofCabsAlloted;
	private String destLatitude;
	private String destLongitude;
	private String destDateTime;
	private String priceEstimate;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private ArrayList<UserDetail> users;

	/**
	 * 
	 * @return The tripId
	 */
	public String getTripId() {
		return tripId;
	}

	/**
	 * 
	 * @param tripId
	 *            The trip_id
	 */
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	/**
	 * 
	 * @return The tripName
	 */
	public String getTripName() {
		return tripName;
	}

	/**
	 * 
	 * @param tripName
	 *            The trip_name
	 */
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	/**
	 * 
	 * @return The tripDestination
	 */
	public String getTripDestination() {
		return tripDestination;
	}

	/**
	 * 
	 * @param tripDestination
	 *            The trip_destination
	 */
	public void setTripDestination(String tripDestination) {
		this.tripDestination = tripDestination;
	}

	/**
	 * 
	 * @return The tripIntialtor
	 */
	public String getTripIntialtor() {
		return tripIntialtor;
	}

	/**
	 * 
	 * @param tripIntialtor
	 *            The trip_intialtor
	 */
	public void setTripIntialtor(String tripIntialtor) {
		this.tripIntialtor = tripIntialtor;
	}

	/**
	 * 
	 * @return The tripPlannedAttendies
	 */
	public String getTripPlannedAttendies() {
		return tripPlannedAttendies;
	}

	/**
	 * 
	 * @param tripPlannedAttendies
	 *            The trip_planned_attendies
	 */
	public void setTripPlannedAttendies(String tripPlannedAttendies) {
		this.tripPlannedAttendies = tripPlannedAttendies;
	}

	/**
	 * 
	 * @return The tripAcceptedAttendies
	 */
	public String getTripAcceptedAttendies() {
		return tripAcceptedAttendies;
	}

	/**
	 * 
	 * @param tripAcceptedAttendies
	 *            The trip_accepted_attendies
	 */
	public void setTripAcceptedAttendies(String tripAcceptedAttendies) {
		this.tripAcceptedAttendies = tripAcceptedAttendies;
	}

	/**
	 * 
	 * @return The tripNumberofCabsAlloted
	 */
	public String getTripNumberofCabsAlloted() {
		return tripNumberofCabsAlloted;
	}

	/**
	 * 
	 * @param tripNumberofCabsAlloted
	 *            The trip_numberof_cabs_alloted
	 */
	public void setTripNumberofCabsAlloted(String tripNumberofCabsAlloted) {
		this.tripNumberofCabsAlloted = tripNumberofCabsAlloted;
	}

	/**
	 * 
	 * @return The destLatitude
	 */
	public String getDestLatitude() {
		return destLatitude;
	}

	/**
	 * 
	 * @param destLatitude
	 *            The dest_latitude
	 */
	public void setDestLatitude(String destLatitude) {
		this.destLatitude = destLatitude;
	}

	/**
	 * 
	 * @return The destLongitude
	 */
	public String getDestLongitude() {
		return destLongitude;
	}

	/**
	 * 
	 * @param destLongitude
	 *            The dest_longitude
	 */
	public void setDestLongitude(String destLongitude) {
		this.destLongitude = destLongitude;
	}

	/**
	 * 
	 * @return The destDateTime
	 */
	public String getDestDateTime() {
		return destDateTime;
	}

	/**
	 * 
	 * @param destDateTime
	 *            The dest_date_time
	 */
	public void setDestDateTime(String destDateTime) {
		this.destDateTime = destDateTime;
	}

	/**
	 * 
	 * @return The priceEstimate
	 */
	public String getPriceEstimate() {
		return priceEstimate;
	}

	/**
	 * 
	 * @param priceEstimate
	 *            The price_estimate
	 */
	public void setPriceEstimate(String priceEstimate) {
		this.priceEstimate = priceEstimate;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public ArrayList<UserDetail> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserDetail> users) {
		this.users = users;
	}

}