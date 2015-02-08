package com.ola.trips;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.MapView;
import com.ola.parsers.AppConst;

public class TripInvite extends ActionBarActivity implements LocationListener {

	private LinearLayout mFriendsLayout;
	private TextView newTv;
	private View friendView;

	// Variable for storing current date and time
	private int mYear, mMonth, mDay, mHour, mMinute;
	private MapView mapView;

	private GoogleMap map;
	private float x1, x2;
	static final int MIN_DISTANCE = 150;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trip_invite_details);
		overridePendingTransition(R.anim.slide_start_right,R.anim.slide_end_left);
		mFriendsLayout = (LinearLayout) findViewById(R.id.friendsLayout);

		for (int i = 0; i < ((AppConst)getApplication()).mTripInviteDetails.size(); i++) 
		{
			newTv = new TextView(getApplicationContext());
			newTv.setPadding(20, 20, 20, 20);
			newTv.setGravity(Gravity.CENTER_HORIZONTAL);
			newTv.setTextSize(20);
			InputFilter[] filterArray = new InputFilter[1];
			filterArray[0] = new InputFilter.LengthFilter(8);
			newTv.setFilters(filterArray);
			newTv.setText(((AppConst)getApplication()).mTripInviteDetails.get(0).getUsers().get(i).getUserName());
			newTv.setCompoundDrawablesWithIntrinsicBounds(0,
					R.drawable.images, 0, 0);
			mFriendsLayout.addView(newTv, 0);
		}

		final EditText date = (EditText) findViewById(R.id.txtDate);

		date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// Process to get Current Date
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				// Launch Date Picker Dialog
				DatePickerDialog dpd = new DatePickerDialog(TripInvite.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								date.setText(dayOfMonth + "-"
										+ (monthOfYear + 1) + "-" + year);

							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		final EditText time = (EditText) findViewById(R.id.txtTime);

		time.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Calendar mcurrentTime = Calendar.getInstance();
				int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
				int minute = mcurrentTime.get(Calendar.MINUTE);
				TimePickerDialog mTimePicker;
				mTimePicker = new TimePickerDialog(TripInvite.this,
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker,
									int selectedHour, int selectedMinute) {
								time.setText(selectedHour + ":"
										+ selectedMinute);
							}
						}, hour, minute, true);// Yes 24 hour time
				mTimePicker.setTitle("Select Time");
				mTimePicker.show();
			}
		});

		// //show error dialog if GoolglePlayServices not available
		// if (!isGooglePlayServicesAvailable()) {
		// finish();
		// }

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map_view)).getMap();
		map.setMyLocationEnabled(true);

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String bestProvider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(bestProvider);
		if (location != null) {
			onLocationChanged(location);
		}
		locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);

		map.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng arg0) {

			}
		});

		Button acceptTrip = (Button) findViewById(R.id.accept);

		acceptTrip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TripInvite.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				TripInvite.this.startActivityForResult(intent, 0);
			}
		});
		Button rejectTrip = (Button) findViewById(R.id.reject);

		rejectTrip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TripInvite.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				TripInvite.this.startActivityForResult(intent, 0);
			}
		});

	}

	@Override
	public void onLocationChanged(Location location) {
		// TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		map.animateCamera(CameraUpdateFactory.zoomTo(16));
		// locationTv.setText("Latitude:" + latitude + ", Longitude:" +
		// longitude);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		overridePendingTransition(R.anim.slide_back_left, R.anim.slide_back_right);
		super.onActivityResult(requestCode, resultCode, data);

	}
}
