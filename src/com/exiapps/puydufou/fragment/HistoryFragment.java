package com.exiapps.puydufou.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exiapps.puydufou.R;

public class HistoryFragment extends AbstractFragment {

	public HistoryFragment() {
		this.title = "Historique";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_historique, container, false);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
