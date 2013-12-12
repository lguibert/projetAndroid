package com.exiapps.puydufou.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exiapps.puydufou.R;

public class InformationFragment extends AbstractFragment {
	private View view;

	public InformationFragment() {
		this.title = "Information Pratique";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.view = inflater.inflate(R.layout.fragment_information_pratique, container, false);
		TextView textView = (TextView) view.findViewById(R.id.textViewClickLink);
		textView.setClickable(true);
		return view;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void onLinkClicked() {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) getActivity().getResources().getText(R.string.link)));
		startActivity(intent);
	}
}
