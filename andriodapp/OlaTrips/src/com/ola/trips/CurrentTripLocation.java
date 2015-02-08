package com.ola.trips;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.ola.parsers.AppConst;

public class CurrentTripLocation extends ActionBarActivity  {


	GoogleMap map;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_currenttrip);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMyLocationEnabled(true);
		
		int i = getIntent().getIntExtra("index", 1);
		
		if(i==1){
			map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(((AppConst)getApplication()).mTripInviteDetails.get(0).getUsers().get(0).getUserLatitude(),
					((AppConst)getApplication()).mTripInviteDetails.get(0).getUsers().get(0).getUserLogitude())));
			map.animateCamera(CameraUpdateFactory.zoomTo(12));
		}else if (i==2){
			map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(((AppConst)getApplication()).mTripInviteDetails.get(0).getUsers().get(1).getUserLatitude(),
					((AppConst)getApplication()).mTripInviteDetails.get(0).getUsers().get(1).getUserLogitude())));
			map.animateCamera(CameraUpdateFactory.zoomTo(12));
		}else if (i==3){
			map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(12.94940377,77.64275701)));
			map.animateCamera(CameraUpdateFactory.zoomTo(12));
		}


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
