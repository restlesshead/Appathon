package com.ola.trips;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.ola.beans.TripDetails;
import com.ola.parsers.AppConst;

public class TripInvitations extends ActionBarActivity {

	GoogleMap map;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */

	private ArrayList<TripDetails> tripDetails = new ArrayList<TripDetails>();

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */

	public static TripInvitations newInstance() {
		TripInvitations fragment = new TripInvitations();
		return fragment;
	}

	public TripInvitations() {
	}

	@Override
	public void onCreate(
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trip_invites);
		overridePendingTransition(R.anim.slide_start_right,R.anim.slide_end_left);
		tripDetails = ((AppConst) getApplication()).mTripInviteDetails;

		RelativeLayout item1 = (RelativeLayout) findViewById(R.id.item1);

		item1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TripInvitations.this,
						TripInvite.class);
				TripInvitations.this.startActivityForResult(intent,0);

			}
		});

		ImageView item1Avatar = (ImageView) findViewById(R.id.avatar);
		item1Avatar.setImageResource(R.drawable.birthday);

		TextView item1Name = (TextView) findViewById(R.id.name);
		item1Name.setText(tripDetails.get(0).getTripName());

		TextView item1numberofPeople = (TextView)findViewById(R.id.numberofPeople);
		item1numberofPeople.setText(tripDetails.get(0).getTripDestination());

		TextView item1status = (TextView) findViewById(R.id.status);
		item1status.setText("UPCOMING");

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
		super.onActivityResult(requestCode, resultCode, data);

	}
}
