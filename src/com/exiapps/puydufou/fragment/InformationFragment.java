package com.exiapps.puydufou.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exiapps.puydufou.R;

public class InformationFragment extends AbstractFragment {

	public InformationFragment() {
		this.title = "Information Pratique";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_information_pratique, container, false);
	}

	@Override
	public void onResume() {
		String comments = getResources().getString(R.string.link);
		final TextView commentsText = (TextView) getActivity().findViewById(R.id.textViewClickLink);
		commentsText.setMovementMethod(LinkMovementMethod.getInstance());
		commentsText.setText(Html.fromHtml(comments));
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
