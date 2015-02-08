package com.ola.trips;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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


public class NewTrip extends ActionBarActivity implements LocationListener {

	private LinearLayout mFriendsLayout;
	private TextView newTv;
	private View friendView;

	// Variable for storing current date and time
	private int mYear, mMonth, mDay, mHour, mMinute;
	private MapView mapView;
	private LinearLayout.LayoutParams lp;
	private GoogleMap map;
	private float x1,x2;
	static final int MIN_DISTANCE = 150;
	private int xCord;
	private int yCord;
	private int[] drawableList = { R.drawable.sasikanth, R.drawable.venky, R.drawable.mohan, R.drawable.sreekanth};
	private int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newtrip);

		overridePendingTransition(R.anim.slide_start_right,R.anim.slide_end_left);

		mFriendsLayout = (LinearLayout) findViewById(R.id.friendsLayout); 
		TextView add =  (TextView) findViewById(R.id.add); 

		final EditText eventName = (EditText) findViewById(R.id.eventName);

		add.setOnClickListener(new OnClickListener() {



			@Override
			public void onClick(View v) {

				Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
				startActivityForResult(contactPickerIntent, 0);

				newTv =  new TextView(getApplicationContext());

				newTv.setPadding(20, 20, 20, 20);
				newTv.setGravity(Gravity.CENTER_HORIZONTAL);
				newTv.setTextSize(16);
				newTv.setTextColor(Color.parseColor("#D4D828"));
				//				newTv.setw




				InputFilter[] filterArray = new InputFilter[1];
				filterArray[0] = new InputFilter.LengthFilter(8);
				newTv.setFilters(filterArray);

				newTv.setOnTouchListener(new OnTouchListener() {



					@Override
					public boolean onTouch(View v, MotionEvent event) {

						if(event.getAction() == MotionEvent.ACTION_DOWN){
							xCord = (int) event.getX();
							yCord = (int) event.getY();
						}
						return false;
					}
				});


				newTv.setOnClickListener(new OnClickListener() {



					@Override
					public void onClick(View v) {
						// custom dialog
						final Dialog dialog1 = new Dialog(NewTrip.this);
						friendView = v;
						dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
						dialog1.setContentView(R.layout.frienddetails);



						WindowManager.LayoutParams wmlp = dialog1.getWindow().getAttributes();
						wmlp.gravity = Gravity.TOP | Gravity.LEFT;


						DisplayMetrics dimension = new DisplayMetrics();
						getWindowManager().getDefaultDisplay().getMetrics(dimension);
						int width = dimension.widthPixels;
						int height = dimension.heightPixels;

						wmlp.x = 40;   //x position
						wmlp.y = 698;   //y position

						// set the custom dialog components - text, image and button
						TextView name = (TextView) dialog1.findViewById(R.id.name);
						name.setText("A Mohan");
						ImageView image = (ImageView) dialog1.findViewById(R.id.delete);

						image.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub



								new AlertDialog.Builder(NewTrip.this)
								.setTitle("Confirmation")
								.setMessage("Are you sure you want to delete")
								.setPositiveButton("ok",
										new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												if(which==1){
													dialog.dismiss();
													dialog1.dismiss();
													mFriendsLayout.removeView(friendView);



												}
											}
										}.onClick(dialog, 1);
									}
								})
								.setNegativeButton("cancel",
										new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												if(which==1){


													dialog.dismiss();
												}
											}
										}.onClick(dialog, 0);
									}
								}).show();
							}
						});
						//						ImageView image = (ImageView) dialog.findViewById(R.id.image);
						//						image.setImageResource(R.drawable.img_big);

						//						Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
						//						// if button is clicked, close the custom dialog
						//						dialogButton.setOnClickListener(new OnClickListener() {
						//							@Override
						//							public void onClick(View v) {
						//								dialog.dismiss();
						//							}
						//						});

						dialog1.show();
					}

				});

			}
		});

		final EditText date =  (EditText) findViewById(R.id.txtDate); 

		date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {


				// Process to get Current Date
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				// Launch Date Picker Dialog
				DatePickerDialog dpd = new DatePickerDialog(NewTrip.this,
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// Display Selected date in textbox
						date.setText(dayOfMonth + "-"
								+ (monthOfYear + 1) + "-" + year);

					}
				}, mYear, mMonth, mDay);
				Date newDate = c.getTime();
				dpd.getDatePicker().setMinDate(newDate.getTime());
				dpd.show();
			}
		});

		final EditText time =  (EditText) findViewById(R.id.txtTime); 

		time.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {


				Calendar mcurrentTime = Calendar.getInstance();
				int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
				int minute = mcurrentTime.get(Calendar.MINUTE);
				TimePickerDialog mTimePicker;
				mTimePicker = new TimePickerDialog(NewTrip.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
						time.setText( selectedHour + ":" + selectedMinute);
					}
				}, hour, minute, true);//Yes 24 hour time
				mTimePicker.setTitle("Select Time");
				mTimePicker.show();
			}
		});

		//		//show error dialog if GoolglePlayServices not available
		//		if (!isGooglePlayServicesAvailable()) {
		//			finish();
		//		}

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_view))
				.getMap();
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
				Intent i = new Intent(NewTrip.this, CurrentTripLocation.class);
				i.putExtra("index", 3);
				startActivityForResult(i, 1000);

			}
		});

		Button createTrip = (Button) findViewById(R.id.create);



		createTrip.setOnClickListener(new OnClickListener() {

			private String title;
			private String message;

			@Override
			public void onClick(View v) {

				if(eventName.getText()!=null && eventName.getText().toString().trim().length() > 0 && mFriendsLayout.getChildCount()>1 ){
					//				Intent intent = new Intent(NewTrip.this, MyTrips.class);
					//				NewTrip.this.startActivity(intent);

					AlertDialog.Builder dialog = new AlertDialog.Builder(NewTrip.this)
					.setTitle("Information")
					.setMessage("Your Trip processed successfully")
					.setNeutralButton("ok",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									if(which==1){
										dialog.dismiss();
										NewTrip.this.finish();
									}
								}
							}.onClick(dialog, 1);
						}
					});

					dialog.show();

				}else{
					AlertDialog.Builder dialog = new AlertDialog.Builder(NewTrip.this)
					.setTitle("Information")
					.setMessage("Trip cannot be processed. Please check if you have entered Event Name, Date and added a invitee ")
					.setNeutralButton("ok",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									if(which==1){
										dialog.dismiss();
									}
								}
							}.onClick(dialog, 1);
						}
					});

					dialog.show();
				}
				
				

			}
		});

	}

	private boolean isGooglePlayServicesAvailable() {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (ConnectionResult.SUCCESS == status) {
			return true;
		} else {
			GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
			return false;
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		//    TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		map.animateCamera(CameraUpdateFactory.zoomTo(16));
		// locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
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
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0:
				Cursor cursor = null;

				try {
					Uri result = data.getData();
					String id = result.getLastPathSegment();

					//Get Name
					cursor = getContentResolver().query(result, null, null, null, null);
					if (cursor.moveToFirst()) {
						String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
						Uri photo = Uri.withAppendedPath(result, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
						newTv.setText(name);
						String pictureID = Uri.withAppendedPath(result, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY).toString();
						InputStream inputStream = getContentResolver().openInputStream(photo);

						Drawable drawable = Drawable.createFromStream(inputStream, pictureID );
						newTv.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.images, 0, 0);
						newTv.setTransitionName("move");
						index++;
						mFriendsLayout.addView(newTv,0);

					}
				} 

				catch (FileNotFoundException e) {
					newTv.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.images, 0, 0);
					mFriendsLayout.addView(newTv,0);
				}
			}
		}
	}

	

}
