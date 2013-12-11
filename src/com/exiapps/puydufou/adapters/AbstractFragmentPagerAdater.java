package com.exiapps.puydufou.adapters;

import java.util.List;
import java.util.Vector;

import com.exiapps.puydufou.fragment.AbstractFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class AbstractFragmentPagerAdater extends FragmentPagerAdapter {
	protected List<AbstractFragment> fragments;

	public AbstractFragmentPagerAdater(FragmentManager fragmentManager) {
		super(fragmentManager);
		this.fragments = new Vector<AbstractFragment>();
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
		
		AbstractFragment fragment = this.fragments.get(position);
		return fragment.getTitle();
	}

	public void clear() {
		this.fragments.clear();
	}
}