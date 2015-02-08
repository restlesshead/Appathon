package com.ola.parsers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Application;

import com.ola.beans.CabDetail;
import com.ola.beans.TripDetails;
import com.ola.beans.UserDetail;

public class AppConst extends Application {

	private JSONObject tripObj;
	public ArrayList<TripDetails> mTripDetails;
	public ArrayList<TripDetails> mTripInviteDetails;
	public ArrayList<CabDetail> cabDetails;

	@Override
	public void onCreate() {
		super.onCreate();
		parseTripsJSON();
		parseTripInvitesJSON();
	}

	private void parseCabListJSON() {
		InputStream jsonIpStream = null;
		try {
			jsonIpStream = getAssets().open("trips.json");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ByteArrayOutputStream jsonByteArrayOpStream = new ByteArrayOutputStream();
		int ctrTrip;
		try {
			ctrTrip = jsonIpStream.read();
			while (ctrTrip != -1) {
				jsonByteArrayOpStream.write(ctrTrip);
				ctrTrip = jsonIpStream.read();
			}
			jsonIpStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			cabDetails = new ArrayList<CabDetail>();
			String tripbos = jsonByteArrayOpStream.toString();
			tripObj = new JSONObject(tripbos);
			JSONArray tripArray = tripObj.getJSONArray("trip_cabs_details");
			for (int i = 0; i < tripArray.length(); i++) {
				CabDetail cabDetail = new CabDetail();
				JSONObject cabObjDetail = tripArray.getJSONObject(i);
				cabDetail.setCabId(cabObjDetail.getString("display_name"));
				cabDetail.setCabType(cabObjDetail.getString("display_name"));
				// cabDetail.setcabLowEstimate(cabObjDetail.getString("low_estimate"));
				// cabDetail.setcabHighEstimate(cabObjDetail.getString("high_estimate"));
				// cabDetail.setcabActualCost(cabObjDetail.getString("actual_cost"));
				cabDetail
						.setCabDuration(cabObjDetail.getString("display_name"));
				// cabDetail.setcabDistance(cabObjDetail.getString("distance"));
				cabDetail.setCapacity(cabObjDetail.getString("capacity"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void parseTripsJSON() {
		InputStream jsonIpStream = null;
		try {
			jsonIpStream = getAssets().open("triplist.json");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ByteArrayOutputStream jsonByteArrayOpStream = new ByteArrayOutputStream();
		int ctrTrip;
		try {
			ctrTrip = jsonIpStream.read();
			while (ctrTrip != -1) {
				jsonByteArrayOpStream.write(ctrTrip);
				ctrTrip = jsonIpStream.read();
			}
			jsonIpStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			mTripDetails = new ArrayList<TripDetails>();

			String tripbos = jsonByteArrayOpStream.toString();
			tripObj = new JSONObject(tripbos);
			JSONArray tripArray = tripObj.getJSONArray("trip_details");
			for (int i = 0; i < tripArray.length(); i++) {
				TripDetails tripDetails = new TripDetails();
				JSONObject tripObjDetail = tripArray.getJSONObject(i);
				tripDetails.setTripId(tripObjDetail.getString("_id"));
				;
				tripDetails.setTripName(tripObjDetail.getString("trip_name"));
				tripDetails.setTripDestination(tripObjDetail
						.getString("trip_destination"));
				tripDetails.setTripIntialtor(tripObjDetail
						.getString("trip_intialtor"));
				tripDetails.setTripPlannedAttendies(tripObjDetail
						.getString("trip_planned_attendies"));
				tripDetails.setTripAcceptedAttendies(tripObjDetail
						.getString("trip_accepted_attendies"));
				tripDetails.setTripNumberofCabsAlloted(tripObjDetail
						.getString("trip_numberof_cabs_alloted"));
				tripDetails.setPriceEstimate(tripObjDetail
						.getString("price_estimate"));
				tripDetails.setDestLatitude(tripObjDetail
						.getString("dest_latitude"));
				tripDetails.setDestLongitude(tripObjDetail
						.getString("dest_longitude"));
				tripDetails.setDestDateTime(tripObjDetail
						.getString("dest_date_time"));
				mTripDetails.add(tripDetails);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void parseTripInvitesJSON() 
	{
		InputStream jsonIpStream = null;
		try {
			jsonIpStream = getAssets().open("userdetails.json");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ByteArrayOutputStream jsonByteArrayOpStream = new ByteArrayOutputStream();
		int ctrTrip;
		try {
			ctrTrip = jsonIpStream.read();
			while (ctrTrip != -1) {
				jsonByteArrayOpStream.write(ctrTrip);
				ctrTrip = jsonIpStream.read();
			}
			jsonIpStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			mTripInviteDetails = new ArrayList<TripDetails>();

			String tripbos = jsonByteArrayOpStream.toString();
			tripObj = new JSONObject(tripbos);
			JSONArray tripArray = tripObj.getJSONArray("trip_details");
			for (int i = 0; i < tripArray.length(); i++) 
			{
				TripDetails tripDetails = new TripDetails();
				JSONObject tripObjDetail = tripArray.getJSONObject(i);
				tripDetails.setTripId(tripObjDetail.getString("_id"));
				;
				tripDetails.setTripName(tripObjDetail.getString("trip_name"));
				tripDetails.setTripDestination(tripObjDetail
						.getString("trip_destination"));
				tripDetails.setTripIntialtor(tripObjDetail
						.getString("trip_intialtor"));
				tripDetails.setTripPlannedAttendies(tripObjDetail
						.getString("trip_planned_attendies"));
				tripDetails.setTripAcceptedAttendies(tripObjDetail
						.getString("trip_accepted_attendies"));
				tripDetails.setTripNumberofCabsAlloted(tripObjDetail
						.getString("trip_numberof_cabs_alloted"));
				tripDetails.setPriceEstimate(tripObjDetail
						.getString("price_estimate"));
				tripDetails.setDestLatitude(tripObjDetail
						.getString("dest_latitude"));
				tripDetails.setDestLongitude(tripObjDetail
						.getString("dest_longitude"));
				tripDetails.setDestDateTime(tripObjDetail
						.getString("dest_date_time"));
				
				
				tripDetails.setUsers(new ArrayList<UserDetail>());
				JSONArray invitees = tripObjDetail.getJSONArray("trip_user_details");
				
				for(int j = 0; j < invitees.length();j++)
				{
					JSONObject userObj = invitees.getJSONObject(j);
					UserDetail userDetail = new UserDetail();
					userDetail.setUserName(userObj.getString("user_name"));
					userDetail.setNumberOfTravellers(userObj.getString("number_of_travellers"));
					userDetail.setUserAcceptedInvite("user_accepted_invite");
					userDetail.setUserId(userObj.getString("user_id"));
					userDetail.setUserLatitude(userObj.getDouble("user_latitude"));
					userDetail.setUserLogitude(userObj.getDouble("user_longitude"));
					tripDetails.getUsers().add(userDetail);
				}
				mTripInviteDetails.add(tripDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
