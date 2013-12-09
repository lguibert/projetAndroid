package com.exiapps.puydufou.view;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.arrayadapters.DrawerArrayAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements ListView.OnItemClickListener  {



	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		this.listView = (ListView) findViewById(R.id.left_drawer);
		this.listView.setAdapter(new DrawerArrayAdapter(this, R.layout.drawer_list_item));		
		this.listView.setOnItemClickListener((OnItemClickListener)this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		//TODO Ouverture de l'activité
	}
}
