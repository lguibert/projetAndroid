package com.exiapps.puydufou.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.exiapps.puydufou.fragment.HistoryFragment;

public class InformationPagerAdapter extends AbstractFragmentPagerAdater {
	public InformationPagerAdapter(FragmentManager fragmentManager, Activity activity) {
		super(fragmentManager);
		fragments.add(Fragment.instantiate(activity, HistoryFragment.class.getName()));
		fragments.add(Fragment.instantiate(activity, HistoryFragment.class.getName()));
		fragments.add(Fragment.instantiate(activity, HistoryFragment.class.getName()));
	}

}
