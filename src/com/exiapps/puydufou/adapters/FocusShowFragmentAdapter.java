package com.exiapps.puydufou.adapters;

import com.exiapps.puydufou.fragment.FocusShowFragment;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

public class FocusShowFragmentAdapter extends AbstractFragmentPagerAdater{
	
	public FocusShowFragmentAdapter(FragmentManager fragmentManager, Activity activity){
		super(fragmentManager);
		
		this.fragments.add(new FocusShowFragment());
	}

}
