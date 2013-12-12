package com.exiapps.puydufou.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
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
import android.widget.CheckBox;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.AbstractFragmentPagerAdater;
import com.exiapps.puydufou.adapters.DrawerArrayAdapter;
import com.exiapps.puydufou.adapters.InformationPagerAdapter;
import com.exiapps.puydufou.adapters.MapPagerAdapter;
import com.exiapps.puydufou.adapters.ScheduleFragmentPagerAdapter;
import com.exiapps.puydufou.fragment.MapFragments;

public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener, ActionBar.TabListener {
	private ListView listView;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout drawerLayout;
	private AbstractFragmentPagerAdater mPagerAdapter;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

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

		switch (position) {
		case 0:
			this.mPagerAdapter = new MapPagerAdapter(super.getSupportFragmentManager(), this);			
			this.pager.setAdapter(this.mPagerAdapter);
			this.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		case 1:
			this.mPagerAdapter = new ScheduleFragmentPagerAdapter(super.getSupportFragmentManager(), this);			
			this.pager.setAdapter(this.mPagerAdapter);
			this.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			this.initTab();
			break;
		case 2:
			this.mPagerAdapter = new InformationPagerAdapter(super.getSupportFragmentManager(), this);
			
			this.pager.setAdapter(this.mPagerAdapter);
			this.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			this.initTab();
			break;
		default: {
		}

		}

		this.drawerLayout.closeDrawers();
	}

	private void initTab() {

		getActionBar().removeAllTabs();

		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

				getActionBar().setSelectedNavigationItem(position);
			}
		});

		for (int i = 0; i < mPagerAdapter.getCount(); i++) {

			getActionBar().addTab(getActionBar().newTab().setText(mPagerAdapter.getPageTitle(i)).setTabListener((TabListener) this));
		}
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

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		pager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public void onCheckboxClicked(View view) {
		// Is the view now checked?
		boolean checked = ((CheckBox) view).isChecked();

		// Check which checkbox was clicked
		switch (view.getId()) {
		case R.id.checkBoxSpectacle:
			((MapFragments) this.mPagerAdapter.getItem(0)).onCheckboxClicked(R.id.checkBoxSpectacle, checked);
			break;
		case R.id.checkBoxBoutique:
			((MapFragments) this.mPagerAdapter.getItem(0)).onCheckboxClicked(R.id.checkBoxBoutique, checked);
			break;
		case R.id.checkBoxRestaurant:
			((MapFragments) this.mPagerAdapter.getItem(0)).onCheckboxClicked(R.id.checkBoxRestaurant, checked);
			break;
		// TODO: Veggie sandwich
		}
	}

	public AbstractFragmentPagerAdater getmPagerAdapter() {
		return mPagerAdapter;
	}

	public ViewPager getPager() {
		return pager;
	}

	public void setmPagerAdapter(AbstractFragmentPagerAdater mPagerAdapter) {
		this.mPagerAdapter = mPagerAdapter;
	}

	public void setPager(ViewPager pager) {
		this.pager = pager;
	}

}
