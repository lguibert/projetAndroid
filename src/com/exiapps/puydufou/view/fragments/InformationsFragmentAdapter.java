package com.exiapps.puydufou.view.fragments;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class InformationsFragmentAdapter extends FragmentPagerAdapter {

	private List<AbstractFragment> fragments;
	
	public InformationsFragmentAdapter(FragmentManager fm) {
		super(fm);

		this.fragments = new ArrayList<AbstractFragment>();
		
	}

	@Override
	public Fragment getItem(int location) {
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
