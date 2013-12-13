package com.exiapps.puydufou.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.exiapps.puydufou.view.FocusShowActivity;
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
	protected List<Spectacle> spectacles;
	protected List<Service> boutiques;
	protected List<Service> restaurants;
	private List<String> date;

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
		markerRestaurant();
		markerShow();
		markerBoutique();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(PDF, 15));
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setOnMarkerClickListener(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		SupportMapFragment f = ((SupportMapFragment) ((android.support.v4.app.Fragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)));
		if (f != null)
			getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
	}

	@Override
	public void refresh() {
	}

	public void onCheckboxClicked(int id, boolean checked) {
		switch (id) {
		case R.id.checkBoxSpectacle:
			setMarkerVisible(this.show, checked);
			break;
		case R.id.checkBoxBoutique:
			setMarkerVisible(this.boutique, checked);
			break;
		case R.id.checkBoxRestaurant:
			setMarkerVisible(this.restaurant, checked);
			break;
		}
	}

	private void setMarkerVisible(List<Marker> markers, boolean check) {
		for (Marker marker : markers) {
			marker.setVisible(check);
		}
	}

	private void markerShow() {
		this.show = new ArrayList<Marker>();
		SpectacleManager spectacleManager = new SpectacleManager();
		spectacleManager.getAllDetailAsync();
		spectacleManager.setOnReceiveListener(new OnReceiveListener() {
			@Override
			public void OnReceive(Object object) {
				if(!isDetached()){
					spectacles = (List<Spectacle>) object;
					if (spectacles.size() > 0) {
						for (Spectacle spectacle : spectacles) {
							show.add(map.addMarker(new MarkerOptions().position(spectacle.getPosition()).title(spectacle.getNom()).icon(BitmapDescriptorFactory.fromResource(R.drawable.spectacle))));
							nextShow(spectacle.getId(), show.size() - 1);
						}
					}
				}
			}
		});
	}

	private void nextShow(int idShow, final int indice) {
		this.setDate(new ArrayList<String>());
		SpectacleManager spectacleManager = new SpectacleManager();
		spectacleManager.getNextShow(idShow);
		spectacleManager.setOnReceiveListener(new OnReceiveListener() {
			@Override
			public void OnReceive(Object object) {
				if(!isDetached()){			
					List<String> dates = (List<String>) object;
					if (dates.size() > 0) {
						String snippet = ((String) getResources().getText(R.string.next_show)) + " " + dates.get(0).substring(0, dates.get(0).length() - 3) + "\n"
								+ ((String) getResources().getText(R.string.duration)) + " " + String.valueOf(spectacles.get(indice).getDuree()) + " "
								+ ((String) getResources().getText(R.string.time_duration));
						show.get(indice).setSnippet(snippet);
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
				if(!isDetached()){
					boutiques = ((List<Service>) object);
					if (boutiques.size() > 0) {
						for (Service service : boutiques) {
							boutique.add(map.addMarker(new MarkerOptions().position(service.getPosition()).title(service.getNom()).snippet(((String) getResources().getText(R.string.boutique)))
									.icon(BitmapDescriptorFactory.fromResource(R.drawable.boutique))));
						}
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
				if(!isDetached()){
					restaurants = ((List<Service>) object);
					if (restaurants.size() > 0) {
						for (Service service : restaurants) {
							restaurant.add(map.addMarker(new MarkerOptions().position(service.getPosition()).title(service.getNom()).snippet(((String) getResources().getText(R.string.restaurant)))
									.icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant))));
						}
					}
				}				
			}
		});
	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		final CharSequence[] items = { marker.getSnippet(), (String) getResources().getText(R.string.btn_info), (String) getResources().getText(R.string.btn_nav) };
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(marker.getTitle());
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if (item == items.length - 1) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + String.valueOf(marker.getPosition().latitude) + ","
							+ String.valueOf(marker.getPosition().longitude + "&dirflg=w")));
					startActivity(intent);
				}
				if (item == items.length - 2) {
					int indice = indice(marker);
					if (indice >= 0) {
						Intent i = new Intent(getActivity().getApplicationContext(), FocusShowActivity.class);
						i.putExtra("id", indice);
						startActivity(i);
					}
				}
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
		return true;
	}

	public int indice(Marker marker) {
		int indice = -1;
		for (Spectacle spectacle : this.spectacles) {
			if (spectacle.getNom().toString().equals(marker.getTitle().toString())) {
				indice = spectacle.getId();
			}
		}
		return indice;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}
}