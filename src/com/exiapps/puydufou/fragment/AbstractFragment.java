package com.exiapps.puydufou.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

public abstract class AbstractFragment extends Fragment {
	
	protected View view;
	protected String title;
	
	public abstract void refresh();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
