package com.exiapps.puydufou.adapters;

import com.exiapps.puydufou.fragment.ScheduleFragment;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

public class ScheduleFragmentPagerAdapter extends AbstractFragmentPagerAdater {

	public ScheduleFragmentPagerAdapter(FragmentManager fragmentManager,
			Activity activityivity) {
		super(fragmentManager);

		this.fragments.add(new ScheduleFragment());
	}

}
