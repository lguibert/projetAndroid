package com.exiapps.puydufou.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.ServiceManager;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Service;
import com.exiapps.puydufou.model.entities.Spectacle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragments extends AbstractFragment implements InfoWindowAdapter, OnInfoWindowClickListener {
	static final LatLng PDF = new LatLng(46.889271, -0.930117);
	private GoogleMap map;
	private List<Marker> show;
	private List<Marker> restaurant;
	private List<Marker> boutique;
	private View view;
	protected List<Spectacle> spectacles;
	protected List<Service> boutiques;
	protected List<Service> restaurants;

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
		markerShow();
		markerBoutique();
		markerRestaurant();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(PDF, 15));
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setInfoWindowAdapter(this);
	}

	/*
	 * Intent intent = new Intent(Intent.ACTION_VIEW,
	 * Uri.parse("http://maps.google.com/maps?daddr=" +
	 * String.valueOf(marker.getPosition().latitude) + "," +
	 * String.valueOf(marker.getPosition().longitude + "&dirflg=w")));
	 * startActivity(intent);
	 */

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		SupportMapFragment f = ((SupportMapFragment) ((android.support.v4.app.Fragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)));
		if (f != null)
			getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public View getInfoContents(Marker marker) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		// Getting view from the layout file info_window_layout
		View view = getActivity().getLayoutInflater().inflate(R.layout.map_spectacle_layout, null);

		TextView name = (TextView) view.findViewById(R.id.nomSpesctacle);

		name.setText(marker.getTitle());

		// Returning the view containing InfoWindow contents
		return view;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		final CharSequence[] items = { "message", "call" };

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Call");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if (item == 0) {
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				} else {
					System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
				}

			}
		});
	}

	private void markerShow() {
		this.show = new ArrayList<Marker>();
		SpectacleManager spectacleManager = new SpectacleManager();
		spectacleManager.getAllDetailAsync();
		spectacleManager.setOnReceiveListener(new OnReceiveListener() {

			@Override
			public void OnReceive(Object object) {
				spectacles = (List<Spectacle>) object;
				if (spectacles.size() > 0) {
					for (Spectacle spectacle : spectacles) {
						show.add(map.addMarker(new MarkerOptions().position(spectacle.getPosition()).title(spectacle.getNom()).icon(BitmapDescriptorFactory.fromResource(R.drawable.spectacle))));
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
				boutiques = ((List<Service>) object);
				if (boutiques.size() > 0) {
					for (Service service : boutiques) {
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
				restaurants = ((List<Service>) object);
				if (restaurants.size() > 0) {
					for (Service service : restaurants) {
						restaurant.add(map.addMarker(new MarkerOptions().position(service.getPosition()).title(service.getNom()).icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant))));
					}
				}
			}
		});

	}
}
