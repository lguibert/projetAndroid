package com.exiapps.puydufou.model.entities;

import com.google.android.gms.maps.model.LatLng;

public class Spectacle {
	private int id;
	private String nom;
	private String info;
	private int duree;
	private String date;
	private int nbActeur;
	private LatLng position;
	private String image;
	private String[] hours;

	public Spectacle() {
	}

	public Spectacle(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Spectacle(int id, String nom, String info, int duree, String date, int nbActeur, double latitude, double longitude, String image) {
		super();
		this.id = id;
		this.nom = nom;
		this.info = info;
		this.duree = duree;
		this.date = date;
		this.nbActeur = nbActeur;
		this.position = new LatLng(latitude, longitude);
		this.image = image;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNbActeur() {
		return nbActeur;
	}

	public void setNbActeur(int nbActeur) {
		this.nbActeur = nbActeur;
	}

	public LatLng getPosition() {
		return position;
	}

	public void setPosition(LatLng position) {
		this.position = position;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String[] getHours() {
		return hours;
	}

	public void setHours(String[] hours) {
		this.hours = hours;
	}

}
