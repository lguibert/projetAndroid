package com.exiapps.puydufou.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;

public class FocusShowActivity extends Activity {
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus_show);
		Intent i = getIntent();
		this.id = i.getIntExtra("id", 0);

		SpectacleManager sm = new SpectacleManager();
		setTitle("");

		sm.getById(this.id);
		sm.setOnReceiveListener(new OnReceiveListener() {

			@Override
			public void OnReceive(Object object) {
				Spectacle spectacle = (Spectacle) object;

				System.out.println("ID DANS FOCUS: " + (spectacle.getId()));

				setTitle(spectacle.getNom());

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.focus_show, menu);
		return true;
	}

}
