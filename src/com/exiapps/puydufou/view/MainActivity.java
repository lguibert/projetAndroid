package com.exiapps.puydufou.view;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.AbstractFragmentPagerAdater;
import com.exiapps.puydufou.adapters.DrawerArrayAdapter;
import com.exiapps.puydufou.adapters.InformationPagerAdapter;
import com.exiapps.puydufou.adapters.MapPagerAdapter;

public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener {
	private ListView listView;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout drawerLayout;
	private AbstractFragmentPagerAdater mPagerAdapter;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.listView = (ListView) findViewById(R.id.left_drawer);
		this.listView.setAdapter(new DrawerArrayAdapter(this, R.layout.drawer_list_item));
		this.listView.setOnItemClickListener((OnItemClickListener) this);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		this.mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_launcher, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(R.string.title);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(R.string.title_after_open);
				invalidateOptionsMenu();
			}
		};

		this.drawerLayout.setDrawerListener(mDrawerToggle);

		this.mPagerAdapter = new MapPagerAdapter(super.getSupportFragmentManager(), this);
		this.pager = (ViewPager) super.findViewById(R.id.viewpager);
		this.pager.setAdapter(this.mPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		for (int i = 0; i < this.mPagerAdapter.getCount(); i++) {
			getSupportFragmentManager().beginTransaction().remove(this.mPagerAdapter.getItem(i)).commit();
		}
		this.mPagerAdapter.clear();
		this.pager.removeAllViews();
		this.pager.setAdapter(null);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + mPagerAdapter.toString());
		switch (position) {
		case 0:
			this.mPagerAdapter = new MapPagerAdapter(super.getSupportFragmentManager(), this);
			break;
		case 1:
			this.mPagerAdapter = new InformationPagerAdapter(super.getSupportFragmentManager(), this);
			break;
		default:
			this.mPagerAdapter = new InformationPagerAdapter(super.getSupportFragmentManager(), this);
			break;
		}
		System.out.println("cccccccccccccccccccccccccccccccc " + mPagerAdapter.toString());
		try {
			this.pager.setAdapter(this.mPagerAdapter);
		} catch (Exception e) {
			System.out.println("error :" + e.toString());
		}
		System.out.println("dddddddddddddddddddddddddddddd  " + mPagerAdapter.toString());
		this.drawerLayout.closeDrawers();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
