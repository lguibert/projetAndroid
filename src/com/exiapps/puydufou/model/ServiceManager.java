package com.exiapps.puydufou.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exiapps.puydufou.model.entities.Service;

public class ServiceManager extends AbstractManager {
	private List<Service> services;

	public ServiceManager() {

	}

	public void getAllBoutiqueDetailAsync() {

		final Runnable runInUIThread = new Runnable() {
			public void run() {
				onReceiveListener.OnReceive(services);
			}
		};

		new Thread() {
			@Override
			public void run() {
				services = new ArrayList<Service>();

				JSONArray array = new JSONArray();

				array = readJsonArray(BASE_URI + "?type=service&var=allStore");
				for (int i = 0; i < array.length(); i++) {

					JSONObject jSpectacle = null;

					try {

						jSpectacle = array.getJSONObject(i);

						Service service = new Service(jSpectacle.getInt("IDSERVICE"), jSpectacle.getString("NOMSERVICE"), jSpectacle.getDouble("LATITUDESERVICE"),
								jSpectacle.getDouble("LONGITUDESERVICE"));
						services.add(service);

					} catch (JSONException e) {
					}
				}
				uiThreadCallback.post(runInUIThread);
			}
		}.start();
	}

	public void getAllRestaurantDetailAsync() {

		final Runnable runInUIThread = new Runnable() {
			public void run() {
				onReceiveListener.OnReceive(services);
			}
		};

		new Thread() {
			@Override
			public void run() {
				services = new ArrayList<Service>();

				JSONArray array = new JSONArray();

				array = readJsonArray(BASE_URI + "?type=service&var=allRest");
				for (int i = 0; i < array.length(); i++) {

					JSONObject jSpectacle = null;

					try {

						jSpectacle = array.getJSONObject(i);

						Service service = new Service(jSpectacle.getInt("IDSERVICE"), jSpectacle.getString("NOMSERVICE"), jSpectacle.getDouble("LATITUDESERVICE"),
								jSpectacle.getDouble("LONGITUDESERVICE"));
						services.add(service);

					} catch (JSONException e) {
					}
				}
				uiThreadCallback.post(runInUIThread);
			}
		}.start();
	}
}
