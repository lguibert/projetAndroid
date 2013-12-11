package com.exiapps.puydufou.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

public abstract class AbstractFragment extends Fragment {
	
	protected View view;
	
	public abstract void refresh();
}
