package com.exiapps.puydufou.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exiapps.puydufou.model.entities.Spectacle;

public class SpectacleManager extends AbstractManager {
	
	
	
	public SpectacleManager(){
		
	}
	
	public List<Spectacle> getAll(){
		List<Spectacle> spectacles = new ArrayList<Spectacle>();
		
		JSONArray array = this.readJsonArray(BASE_URI + "?type=select&var=name");
		
		for (int i = 0; i < array.length(); i++) {
			
			JSONObject jSpectacle = null;
			
			try {
				
				jSpectacle = array.getJSONObject(i);
				
				Spectacle spectacle = new Spectacle(jSpectacle.getInt("idspectacle"), jSpectacle.getString("nomspectacle"));
				
				spectacles.add(spectacle);
				
			} catch (JSONException e) {
			}
		}
		
		return spectacles;
	}
	
}
