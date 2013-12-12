package com.exiapps.puydufou.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exiapps.puydufou.bestschedule.Representation;
import com.exiapps.puydufou.bestschedule.Time;
import com.exiapps.puydufou.bestschedule.TimeUtils;
import com.exiapps.puydufou.model.entities.Spectacle;

public class SpectacleManager extends AbstractManager {

	private List<String> timetable;
	private List<Spectacle> spectacles;
	private List<Spectacle> seens = new ArrayList<Spectacle>();
	private Spectacle spectacle;
	
	public SpectacleManager() {

	}

	public List<Spectacle> getAll() {
		List<Spectacle> spectacles = new ArrayList<Spectacle>();

		JSONArray array = this.readJsonArray(BASE_URI + "?type=select&var=name");

		for (int i = 0; i < array.length(); i++) {

			JSONObject jSpectacle = null;

			try {

				jSpectacle = array.getJSONObject(i);

				Spectacle spectacle = new Spectacle(jSpectacle.getInt("IDSPECTACLE"), jSpectacle.getString("NOMSPECTACLE"));

				spectacles.add(spectacle);

			} catch (JSONException e) {
			}
		}

		return spectacles;
	}

	public void getAllDetailAsync() {

		final Runnable runInUIThread = new Runnable() {
			public void run() {
				onReceiveListener.OnReceive(spectacles);
			}
		};

		new Thread() {
			@Override
			public void run() {
				spectacles = new ArrayList<Spectacle>();

				JSONArray array = new JSONArray();

				array = readJsonArray(BASE_URI + "?type=select&var=all");
				for (int i = 0; i < array.length(); i++) {

					JSONObject jSpectacle = null;

					try {

						jSpectacle = array.getJSONObject(i);

						Spectacle spectacle = new Spectacle(jSpectacle.getInt("IDSPECTACLE"), jSpectacle.getString("NOMSPECTACLE"), jSpectacle.getString("EVENEMENTLIESPECTACLE"),
								jSpectacle.getInt("DUREESPECTACLE"), jSpectacle.getString("DATECREATIONSPECTACLE"), jSpectacle.getInt("NBACTEURSSPECTACLE"), jSpectacle.getDouble("LATITUDESPECTACLE"),
								jSpectacle.getDouble("LONGITUDESPECTACLE"), jSpectacle.getString("IMAGESPECTACLE"));
						
						
						JSONArray jHours = readJsonArray(BASE_URI + "?type=select&var=hour&id=" + spectacle.getId());						
						String hours[] = new String[jHours.length()];
						
						for (int j = 0; j < jHours.length(); j++) {
							hours[j] = jHours.getJSONObject(j).getString("VALEURHORAIRE");
						}
						
						spectacle.setHours(hours);
						
						spectacles.add(spectacle);

					} catch (JSONException e) {
					}
				}
				uiThreadCallback.post(runInUIThread);
			}
		}.start();
	}

	public void getNextShow(final int idShow) {
		final Runnable runInUIThread = new Runnable() {
			public void run() {
				onReceiveListener.OnReceive(timetable);
			}
		};

		new Thread() {
			@Override
			public void run() {
				timetable = new ArrayList<String>();

				JSONArray array = new JSONArray();

				array = readJsonArray(BASE_URI + "?type=select&var=getNextH&id=" + String.valueOf(idShow));
				for (int i = 0; i < array.length(); i++) {

					JSONObject jTime = null;

					try {

						jTime = array.getJSONObject(i);
						String time = jTime.getString("VALEURHORAIRE");

						timetable.add(time);

					} catch (JSONException e) {
					}
				}
				uiThreadCallback.post(runInUIThread);
			}
		}.start();
	}
	
	
	public void getById(final int id){
		
		final Runnable runInUIThread = new Runnable() {
			public void run() {
				onReceiveListener.OnReceive(spectacle);
			}
		};

		new Thread() {
			@Override
			public void run() {
				timetable = new ArrayList<String>();

				JSONArray array = readJsonArray(BASE_URI + "?type=select&var=" + id);

				try {
					
					JSONObject jSpectacle = array.getJSONObject(0);
					
					spectacle = new Spectacle(jSpectacle.getInt("IDSPECTACLE"), jSpectacle.getString("NOMSPECTACLE"), jSpectacle.getString("EVENEMENTLIESPECTACLE"),
							jSpectacle.getInt("DUREESPECTACLE"), jSpectacle.getString("DATECREATIONSPECTACLE"), jSpectacle.getInt("NBACTEURSSPECTACLE"), jSpectacle.getDouble("LATITUDESPECTACLE"),
							jSpectacle.getDouble("LONGITUDESPECTACLE"), jSpectacle.getString("IMAGESPECTACLE"));
					
					
					
				} catch (JSONException e) {

					e.printStackTrace();
				}	
				
				uiThreadCallback.post(runInUIThread);
			}
		}.start();
	}
	
	public List<Representation> getBest(List<Spectacle> spectacles){
		
	
		for (Spectacle spectacle : spectacles) {
			if(spectacle.getId() == 8){
				spectacle.setHours(new String[]  {"20:30"});
			}
		}
		
		List<Representation> representations = new ArrayList<Representation>();

		
		Time currentTime = new Time(9, 0);
		
		int count = 0;
		int idPrev = 0;
		
		
		
		do{
			
			Spectacle spec = getFirst(spectacles, currentTime,idPrev);
			System.out.println(
					currentTime.getHour() + ":" + currentTime.getMinutes() + "-" +
					spec.getNom() + " - " +
					TimeUtils.getFirst(spec.getHours(), currentTime).getHour() + ":" +
					TimeUtils.getFirst(spec.getHours(), currentTime).getMinutes() + " - "+
					spec.getDuree());
			
			
			
			idPrev = spec.getId();
			
			seens.add(spec);
			
			representations.add(new Representation(spec, TimeUtils.getFirst(spec.getHours(), currentTime)));
			
			currentTime = TimeUtils.timeAdd(TimeUtils.getFirst(spec.getHours(), currentTime), spec.getDuree() + 15);
			count++;
		}
		while(count < 12);
		
		return representations;
	}	
	
	private Spectacle getFirst(List<Spectacle> spectacles,Time currentTime,int idPrev){
		
		Spectacle firstSpectacle = null;
		
		int size = 0;
		
			
		
		for (int i = 0; i < spectacles.size(); i++) {
			
			if(!haveSeen(spectacles.get(i))){
				size++;
			}
		}
		
		Time[] firsts = new Time[size];
		
		int n = 0;		
		for (int i = 0; n < size; i++) {
			
			if(!haveSeen(spectacles.get(i))){
				firsts[n] = TimeUtils.getFirst(spectacles.get(i).getHours(),currentTime);
				n++;
				
				//System.out.println(spectacles.get(i).getNom());
			}
		}
		
		Time first = TimeUtils.getFirst(firsts, currentTime);
		
		for (int i = 0; i < spectacles.size(); i++) {

			if(TimeUtils.getFirst(spectacles.get(i).getHours(),currentTime).getHour() == first.getHour() &&
			   TimeUtils.getFirst(spectacles.get(i).getHours(),currentTime).getMinutes() == first.getMinutes() &&
			   !haveSeen(spectacles.get(i))){
				

				firstSpectacle = spectacles.get(i);
				
			}
		}
		
		
		return firstSpectacle;
	}
	
	private boolean haveSeen(Spectacle spec){
		return seens.contains(spec);
	}	

}
