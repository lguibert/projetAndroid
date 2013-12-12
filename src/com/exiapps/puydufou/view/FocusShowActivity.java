package com.exiapps.puydufou.view;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.R.layout;
import com.exiapps.puydufou.R.menu;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

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
				
				setTitle(spectacle.getNom());
				TextView info = (TextView) findViewById(R.id.usefullInfos);				
				TextView duree = (TextView) findViewById(R.id.dureeSpec);
				TextView nbAct = (TextView) findViewById(R.id.nbAct);
				TextView date = (TextView) findViewById(R.id.dateCrea);
				
				info.setText(spectacle.getInfo());
				duree.setText(""+spectacle.getDuree()+" minutes");
				nbAct.setText(""+spectacle.getNbActeur());
				date.setText(spectacle.getDate());
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
