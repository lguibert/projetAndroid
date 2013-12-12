package com.exiapps.puydufou.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;
import com.facebook.Session.StatusCallback;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

public class FocusShowActivity extends Activity {
	private int id;
	private UiLifecycleHelper uiHelper;
	private StatusCallback callback;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		uiHelper = new UiLifecycleHelper(FocusShowActivity.this, callback);
	    uiHelper.onCreate(savedInstanceState);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus_show);
		Intent i = getIntent();
		this.id = i.getIntExtra("id", 0);	
		setTitle("");
		
		Button sendNoteButton = (Button) findViewById(R.id.sendNoteButton);
		final Button shareButton = (Button) findViewById(R.id.shareButton);
		
		final RatingBar rBar = (RatingBar) findViewById(R.id.ratingBar1);
		
		rBar.setStepSize(1);
		
		sendNoteButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//rBar.getRating();		
			}
		});
		
		
		
		
		SpectacleManager sm = new SpectacleManager();
		sm.getById(this.id);
		sm.setOnReceiveListener(new OnReceiveListener() {
			
			@Override
			public void OnReceive(Object object) {
				final Spectacle spectacle = (Spectacle) object;		
				
				setTitle(spectacle.getNom());
				TextView info = (TextView) findViewById(R.id.usefullInfos);				
				TextView duree = (TextView) findViewById(R.id.dureeSpec);
				TextView nbAct = (TextView) findViewById(R.id.nbAct);
				TextView date = (TextView) findViewById(R.id.dateCrea);
				
				info.setText(spectacle.getInfo());
				duree.setText(""+spectacle.getDuree()+" minutes");
				nbAct.setText(""+spectacle.getNbActeur());
				date.setText(spectacle.getDate());
				
				shareButton.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View v) {
						Intent i = new Intent(Intent.ACTION_SEND);
						i.setType("text/plain");
						i.putExtra(Intent.EXTRA_TEXT, "Envoyé depuis l'application Puy Du Fou");
						i.putExtra(Intent.EXTRA_TITLE, spectacle.getNom());
						i.putExtra(Intent.EXTRA_SUBJECT, "Puy du Fou");
						startActivity(Intent.createChooser(i, "Partager avec..."));						
					}
				});
				
				final ImageButton facebook = (ImageButton) findViewById(R.id.shareFacebookButton);
				facebook.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (FacebookDialog.canPresentShareDialog(getApplicationContext(), FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
							FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(FocusShowActivity.this)
							.setLink("http://www.puydufou.com/")
							.setDescription("A vue le spectacle "+spectacle.getNom())							
							.build();
							uiHelper.trackPendingDialogCall(shareDialog.present());	
						}else{
							Toast.makeText(getApplicationContext(), "Pour partager vous devez avoir l'application Facebook installée.", Toast.LENGTH_LONG).show();
						}
					}
				});	
				
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
	        @Override
	        public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	            Log.e("Activity", String.format("Error: %s", error.toString()));
	        }

	        @Override
	        public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	            Log.i("Activity", "Success!");
	        }
	    });
	}
	@Override
	protected void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

}
