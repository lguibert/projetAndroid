package com.exiapps.puydufou.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.exiapps.puydufou.fragment.MapFragments;

public class MapPagerAdapter extends AbstractFragmentPagerAdater {
	public MapPagerAdapter(FragmentManager fragmentManager, Activity activity) {
		super(fragmentManager);
		fragments.add(new MapFragments());
	}
}