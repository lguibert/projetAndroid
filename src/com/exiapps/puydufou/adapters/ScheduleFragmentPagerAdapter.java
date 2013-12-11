package com.exiapps.puydufou.adapters;

import com.exiapps.puydufou.fragment.BestScheduleFragment;
import com.exiapps.puydufou.fragment.ScheduleFragment;
import com.exiapps.puydufou.fragment.UserScheduleFragment;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

public class ScheduleFragmentPagerAdapter extends AbstractFragmentPagerAdater {

	public ScheduleFragmentPagerAdapter(FragmentManager fragmentManager,
			Activity activity) {
		super(fragmentManager);

		this.fragments.add(new ScheduleFragment());
		this.fragments.add(new UserScheduleFragment());
		this.fragments.add(new BestScheduleFragment());
	}

}
