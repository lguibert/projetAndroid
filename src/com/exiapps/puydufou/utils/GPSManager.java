package com.exiapps.puydufou.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GPSManager implements LocationListener {
	
	private Location lastLocation;
	
	private LocationManager locationManger;

	
	public GPSManager(Context context){
		this.locationManger = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		
		this.locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, (LocationListener)this);
		
	}
	
	public Location getLastLocation(){		
		return this.lastLocation;
	}

	@Override
	public void onLocationChanged(Location location) {
		this.lastLocation = location;
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
