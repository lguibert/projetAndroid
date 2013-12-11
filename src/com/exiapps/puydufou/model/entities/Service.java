package com.exiapps.puydufou.model.entities;

import com.google.android.gms.maps.model.LatLng;

public class Service {
	private int id;
	private String nom;
	private LatLng position;

	public Service(int id, String nom, double latitude, double longitude) {
		this.id = id;
		this.nom = nom;
		this.position = new LatLng(latitude, longitude);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LatLng getPosition() {
		return position;
	}

	public void setPosition(LatLng position) {
		this.position = position;
	}

}
