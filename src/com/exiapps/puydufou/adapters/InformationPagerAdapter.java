package com.exiapps.puydufou.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

import com.exiapps.puydufou.fragment.BoutiqueFragment;
import com.exiapps.puydufou.fragment.HistoryFragment;
import com.exiapps.puydufou.fragment.RestaurantFragment;

public class InformationPagerAdapter extends AbstractFragmentPagerAdater {

	public InformationPagerAdapter(FragmentManager fragmentManager, Activity activity) {

		super(fragmentManager);
		fragments.add(new HistoryFragment());
		fragments.add(new HistoryFragment());
		fragments.add(new BoutiqueFragment());
		fragments.add(new RestaurantFragment());
	}

}
