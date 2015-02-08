package com.ola.trips;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.GoogleMap;
import com.ola.beans.TripDetails;
import com.ola.parsers.AppConst;

public class TripItinerary extends ActionBarActivity implements OnClickListener {

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
		setContentView(R.layout.trip_itine);
		overridePendingTransition(R.anim.slide_start_right,
				R.anim.slide_end_left);
		tripDetails = ((AppConst) getApplication()).mTripDetails;

		(findViewById(R.id.item1)).setOnClickListener(this);
		(findViewById(R.id.item2)).setOnClickListener(this);

		// ImageView item1Avatar = (ImageView) findViewById(R.id.layout1);
		// item1Avatar.setImageResource(R.drawable.marriage);

		// ImageView item1Avatar = (ImageView) findViewById(R.id.avatar);
		// item1Avatar.setImageResource(R.drawable.birthday);

		// TextView item1Name= (TextView) findViewById(R.id.name);
		// item1Name.setText(tripDetails.get(0).getTripName());

		// TextView item1numberofPeople= (TextView)
		// findViewById(R.id.numberofPeople);
		// item1numberofPeople.setText(tripDetails.get(0).getTripDestination());

		// TextView item1status= (TextView) findViewById(R.id.status);
		// item1status.setText("UPCOMING");

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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item1:
			Intent i = new Intent(TripItinerary.this, CurrentTripLocation.class);
			i.putExtra("index", 1);
			startActivityForResult(i, 1000);
			break;
		case R.id.item2:
			Intent i1 = new Intent(TripItinerary.this, CurrentTripLocation.class);
			i1.putExtra("index", 2);
			startActivityForResult(i1, 1000);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
		super.onActivityResult(requestCode, resultCode, data);

	}

}
