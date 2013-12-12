package com.exiapps.puydufou.view;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.R.layout;
import com.exiapps.puydufou.R.menu;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FocusShowActivity extends Activity implements OnClickListener {
	
	private int id;
	
	private Button buttonVote;
	private RatingBar ratingBar;
	
	private SpectacleManager sm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus_show);
		Intent i = getIntent();
		this.id = i.getIntExtra("id", 0);		
		
		this.sm = new SpectacleManager();
		
		this.buttonVote = (Button) findViewById(R.id.buttonNote);
		this.ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		
		this.buttonVote.setOnClickListener(this);
		

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

	@Override
	public void onClick(View arg0) {
		int rating  = (int) ratingBar.getRating();
		
		if(rating == 0){rating++;}
		
		sm.rateAsync(id, rating);
		sm.setOnReceiveListener(new OnReceiveListener() {
			
			
			
			@Override
			public void OnReceive(Object object) {
				int note = ((Integer) object).intValue();

				
				if(note >= 0){
					Toast.makeText(FocusShowActivity.this, "Votre vote à bien été pris en compte", Toast.LENGTH_SHORT).show();
					//ratingBar.setRating((float) note);
				}else{
					Toast.makeText(FocusShowActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
				}	
			}
		});
		
	}

}
