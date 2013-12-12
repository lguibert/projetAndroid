package com.exiapps.puydufou.bestschedule;

public class TimeUtils {
	
	public static Time getFirst(String[] strings,Time minTime){
				
		int hMin = 23 ;
		int mMin = 59;
		
		for (int i = 0; i < strings.length; i++) {
			
			Time time = TimeUtils.stringToTime(strings[i]);
			
			int hour = time.getHour();
			int min = time.getMinutes();
		
			if((hour < hMin || (hour == hMin && min < mMin)) && ((hour > minTime.getHour() || ( hour == minTime.getHour() && min >= minTime.getMinutes()))) && hour != 12){
				hMin = time.getHour();
				mMin = time.getMinutes();
			}
		}
		
		return new Time(hMin, mMin);
	}
	public static Time getFirst(Time[] times,Time minTime){
		
		int hMin = 23 ;
		int mMin = 59;
		
		for (int i = 0; i < times.length; i++) {
			
			int hour = times[i].getHour();
			int min = times[i].getMinutes();
		
			if((hour < hMin || (hour == hMin && min < mMin)) && ((hour > minTime.getHour() || ( hour == minTime.getHour() && min >= minTime.getMinutes()))) && hour != 12){
				
				hMin = times[i].getHour();
				mMin = times[i].getMinutes();
			}
		}
		
		return new Time(hMin, mMin);
	}
	public static Time timeAdd(Time time,int minutes){
		
		int m = 0;
		
		int hourToAdd = minutes / 60;
		int minutesToAdd = minutes % 60;
		
		
		
		if(time.getMinutes() + minutesToAdd >= 60){
			hourToAdd++;
			m = (time.getMinutes() + minutesToAdd) - 60;
		}
		else{
			m = time.getMinutes() + minutesToAdd;
		}
		
		
		time.setHour(time.getHour() + hourToAdd);
		time.setMinutes(m);
		
		return time;
	}
	
	public static Time stringToTime(String string){
		
		String sHour =  string.split(":")[0];
		String sMinutes = string.split(":")[1]; 
		
		int hour = Integer.parseInt(sHour);
		int minutes = Integer.parseInt(sMinutes);
		
		return new Time(hour, minutes);
		
	}
}
