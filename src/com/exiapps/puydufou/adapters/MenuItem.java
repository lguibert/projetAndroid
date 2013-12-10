package com.exiapps.puydufou.adapters;

import com.exiapps.puydufou.R;

import android.content.Context;

public enum MenuItem {

	MAP, SCHEDULE, INFO, HISTORIC, RESTAURANTS, SHOPS;


	public String getString(Context context) {
		
		String string = "";
		
		switch (this) {
		
			case MAP:
				string = context.getString(R.string.map);
			break;
			case SCHEDULE:
				string = context.getString(R.string.schedule);
			break;
			case INFO:
				string = context.getString(R.string.info);
			break;
			case HISTORIC:
				string = context.getString(R.string.historic);
			break;		
			case RESTAURANTS:
				string = context.getString(R.string.restaurants);
			break;
			case SHOPS:
				string = context.getString(R.string.shops);
			break;		
		default:
			break;
		}
		
		return string;
	}
}
