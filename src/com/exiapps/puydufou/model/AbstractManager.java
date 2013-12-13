package com.exiapps.puydufou.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;

public abstract class AbstractManager {

	protected final  Handler uiThreadCallback = new Handler();
	
	protected OnReceiveListener onReceiveListener;
	
	protected static String BASE_URI = "http://anthonydenaud.com/projetAndroid/";
	

	protected JSONArray readJsonArray(String uri) {

		JSONArray jsonArray = null;

		try {

			InputStream inputStream = new URL(uri).openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			jsonArray = new JSONArray(readAll(bufferedReader));

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonArray;
	}

	protected JSONObject readJsonObject(String uri) {

		JSONObject json = null;

		try {

			InputStream inputStream = new URL(uri).openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			json = new JSONObject(readAll(bufferedReader));

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json;
	}

	private String readAll(Reader rd) throws IOException {

		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public void setOnReceiveListener(OnReceiveListener listener){
		this.onReceiveListener = listener;
	}
}
