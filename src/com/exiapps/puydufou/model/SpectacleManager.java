package com.exiapps.puydufou.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exiapps.puydufou.model.entities.Spectacle;

public class SpectacleManager extends AbstractManager {

	private List<Spectacle> spectacles;

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
				onReceiveListener.OnReceive(spectacles);
			}
		};

		new Thread() {
			@Override
			public void run() {
				List<String> timetable = new ArrayList<String>();

				JSONArray array = new JSONArray();

				array = readJsonArray(BASE_URI + "?type=select&var=getNextH&id=1" + String.valueOf(idShow));
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

}
