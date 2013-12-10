package com.exiapps.puydufou.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exiapps.puydufou.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragments extends Fragment implements OnMarkerClickListener {
	static final LatLng PDF = new LatLng(46.889271, -0.930117);
	private GoogleMap map;
	private Marker pdf;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_map, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		map = ((MapFragment) ((android.app.Fragment) getActivity().getFragmentManager().findFragmentById(R.id.map))).getMap();
		pdf = map.addMarker(new MarkerOptions().position(PDF).title("Puy du Fou").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(PDF, 15));
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setOnMarkerClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + String.valueOf(marker.getPosition().latitude) + ","
				+ String.valueOf(marker.getPosition().longitude + "&dirflg=w")));
		startActivity(intent);

		return true;
	}

}
