package com.exiapps.puydufou.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.ServiceManager;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Service;
import com.exiapps.puydufou.model.entities.Spectacle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragments extends AbstractFragment implements OnMarkerClickListener {
	static final LatLng PDF = new LatLng(46.889271, -0.930117);
	private GoogleMap map;
	private List<Marker> show;
	private List<Marker> restaurant;
	private List<Marker> boutique;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_map, container, false);
		}
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		map = ((SupportMapFragment) ((android.support.v4.app.Fragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
		markerBoutique();
		markerRestaurant();
		markerShow();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(PDF, 15));
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setOnMarkerClickListener(this);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		/*
		 * Intent intent = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://maps.google.com/maps?daddr=" +
		 * String.valueOf(marker.getPosition().latitude) + "," +
		 * String.valueOf(marker.getPosition().longitude + "&dirflg=w")));
		 * startActivity(intent);
		 */
		marker.showInfoWindow();
		return true;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		SupportMapFragment f = ((SupportMapFragment) ((android.support.v4.app.Fragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)));
		if (f != null)
			getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
	}

	private void markerShow() {
		this.show = new ArrayList<Marker>();
		SpectacleManager spectacleManager = new SpectacleManager();
		spectacleManager.getAllDetailAsync();
		spectacleManager.setOnReceiveListener(new OnReceiveListener() {

			@Override
			public void OnReceive(Object object) {
				List<Spectacle> spectacles = ((List<Spectacle>) object);
				if (spectacles.size() > 0) {
					for (Spectacle spectacle : spectacles) {
						show.add(map.addMarker(new MarkerOptions().position(spectacle.getPosition()).title(spectacle.getNom()).snippet("test")
								.icon(BitmapDescriptorFactory.fromResource(R.drawable.spectacle))));

					}
				}
			}
		});

	}

	private void markerBoutique() {
		this.boutique = new ArrayList<Marker>();
		ServiceManager serviceManager = new ServiceManager();
		serviceManager.getAllBoutiqueDetailAsync();
		serviceManager.setOnReceiveListener(new OnReceiveListener() {

			@Override
			public void OnReceive(Object object) {
				List<Service> services = ((List<Service>) object);
				if (services.size() > 0) {
					for (Service service : services) {
						boutique.add(map.addMarker(new MarkerOptions().position(service.getPosition()).title(service.getNom()).icon(BitmapDescriptorFactory.fromResource(R.drawable.boutique))));
					}
				}
			}
		});

	}

	private void markerRestaurant() {
		this.restaurant = new ArrayList<Marker>();
		ServiceManager serviceManager = new ServiceManager();
		serviceManager.getAllRestaurantDetailAsync();
		serviceManager.setOnReceiveListener(new OnReceiveListener() {

			@Override
			public void OnReceive(Object object) {
				List<Service> services = ((List<Service>) object);
				if (services.size() > 0) {
					for (Service service : services) {
						restaurant.add(map.addMarker(new MarkerOptions().position(service.getPosition()).title(service.getNom()).icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant))));
					}
				}
			}
		});

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
