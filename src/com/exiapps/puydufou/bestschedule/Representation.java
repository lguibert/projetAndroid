package com.exiapps.puydufou.bestschedule;

import com.exiapps.puydufou.model.entities.Spectacle;

public class Representation {
	private Spectacle spectacle;
	private Time time;
	
	
	
	public Representation(Spectacle spectacle, Time time) {
		super();
		this.spectacle = spectacle;
		this.time = time;
	}
	public Spectacle getSpectacle() {
		return spectacle;
	}
	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	
}
