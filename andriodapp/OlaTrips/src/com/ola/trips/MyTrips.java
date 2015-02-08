package com.ola.trips;

import java.util.ArrayList;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.ola.beans.TripDetails;
import com.ola.parsers.AppConst;

public class MyTrips extends ActionBarActivity  {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytrips);

		tripDetails = ((AppConst)getApplication()).mTripDetails;
		overridePendingTransition(R.anim.slide_start_right,R.anim.slide_end_left);
		ImageView item1Avatar = (ImageView) findViewById(R.id.avatar);
		item1Avatar.setImageResource(R.drawable.birthday);

		TextView item1Name= (TextView) findViewById(R.id.name);
		item1Name.setText(tripDetails.get(0).getTripName());

		TextView item1numberofPeople= (TextView) findViewById(R.id.numberofPeople);
		item1numberofPeople.setText(tripDetails.get(0).getTripDestination());

		TextView item1status= (TextView) findViewById(R.id.status);
		item1status.setText("UPCOMING");


		ImageView item2Avatar = (ImageView) findViewById(R.id.avatar2);
		item2Avatar.setImageResource(R.drawable.outing);

		TextView item2Name= (TextView) findViewById(R.id.name2);
		item2Name.setText(tripDetails.get(1).getTripName());

		TextView item2numberofPeople= (TextView) findViewById(R.id.numberofPeople2);
		item2numberofPeople.setText(tripDetails.get(1).getTripDestination());

		TextView item2status= (TextView) findViewById(R.id.status2);
		item2status.setText("COMPLETED");


		final ImageView item3Avatar = (ImageView) findViewById(R.id.avatar3);
		item3Avatar.setImageResource(R.drawable.marriage);

		TextView item3Name= (TextView) findViewById(R.id.name3);
		item3Name.setText(tripDetails.get(2).getTripName());

		TextView item3numberofPeople= (TextView) findViewById(R.id.numberofPeople3);
		item1numberofPeople.setText(tripDetails.get(2).getTripDestination());

		TextView item3status= (TextView) findViewById(R.id.status3);
		item3status.setText("COMPLETED");

		RelativeLayout item1 = (RelativeLayout) findViewById(R.id.item1);

		item1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyTrips.this, TripItinerary.class);
				MyTrips.this.startActivityForResult(intent, 0);

			}
		});

		RelativeLayout item2 = (RelativeLayout) findViewById(R.id.item2);

		item2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyTrips.this, TripItinerary.class);
				MyTrips.this.startActivityForResult(intent, 0);

			}
		});

		RelativeLayout item3 = (RelativeLayout) findViewById(R.id.item3);

		item3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyTrips.this, TripItinerary.class);
				
				
				MyTrips.this.startActivityForResult(intent, 0);

			}
		});


		

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
		super.onActivityResult(requestCode, resultCode, data);

	}



}
