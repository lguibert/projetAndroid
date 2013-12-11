package com.exiapps.puydufou.adapters;

import java.util.List;
import java.util.Vector;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class AbstractFragmentPagerAdater extends FragmentPagerAdapter {
	protected List<Fragment> fragments;

	public AbstractFragmentPagerAdater(FragmentManager fragmentManager) {
		super(fragmentManager);
		this.fragments = new Vector<Fragment>();
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

	
	
	@Override
	public CharSequence getPageTitle(int position) {
		return "";
	}

	public void clear() {
		this.fragments.clear();
	}
}