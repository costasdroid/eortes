package com.example.eortes;

import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.widget.Button;
import android.widget.TextView;

public final class Date {
	private static Calendar today = Calendar.getInstance();
	private static Calendar currentDay = Calendar.getInstance();


	private static TextView lblDate;
	private static TextView txtInfo;
	private static Button buttonToday;
	
	private MyDatabase db;

	public void setDb(Context cntx) {
		this.db = new MyDatabase(cntx);
	}

	@Override
	public String toString() {
		return currentDay.get(Calendar.DATE) + "/" + (currentDay.get(Calendar.MONTH) + 1) + "/" + currentDay.get(Calendar.YEAR);
	}
	
	public void reset() {
		currentDay.set(Calendar.DATE, today.get(Calendar.DATE));
		currentDay.set(Calendar.MONTH, today.get(Calendar.MONTH));
		currentDay.set(Calendar.YEAR, today.get(Calendar.YEAR));
		refresh();
	}

	public boolean isToday() {
		return (today.get(Calendar.DATE) == currentDay.get(Calendar.DATE)
				&& today.get(Calendar.MONTH) == currentDay.get(Calendar.MONTH)
				&& today.get(Calendar.YEAR) == currentDay.get(Calendar.YEAR));
	}
	
	public void add(int dir) {
		switch (dir) {
		case 0:
			currentDay.add(Calendar.MONTH, -1);
			break;
		case 1:
			currentDay.add(Calendar.DATE, -1);
			break;
		case 2:
			currentDay.add(Calendar.MONTH, 1);
			break;
		case 3:
			currentDay.add(Calendar.DATE, 1);
			break;
		}
		refresh();
	}
	
	
	/*
	 * For this method there is a paper "Η Ημερομηνία του Πάσχα, Δημήτρης Ι. Μπουνάκης"
	 * http://users.sch.gr/mmanol/Sunergasies/HmerominiaPasxa.pdf
	 * @returns days of april to Orthodox Eastern
	 */
	private int getEastern(int year) {		
		int k = year / 100 - year / 400 - 2;		
		int a = year % 19;		
		int d = (19 * a + 16) % 30;		
		int s = year / 4;		
		int y = (year + s + d) % 7;		
		return d + k - 4 - y;
	}

	public void refresh() {
		
		lblDate.setText(toString());
	
		String text = "";	

			Cursor c = db.getThisDate(currentDay.get(Calendar.DATE), currentDay.get(Calendar.MONTH) + 1);	
			while (!c.isAfterLast()) {
				if (text.length() > 0) text = text + ", ";
				text = text + c.getString(0);
				c.moveToNext();	
			}
		
		txtInfo.setText(text);

		if (txtInfo.getText() == "") txtInfo.setText("Δεν βρέθηκε γιορτή");
		
		if (isToday()) buttonToday.setVisibility(-1); else buttonToday.setVisibility(1);
	}

	public static void setToday(Calendar today) {
		Date.today = today;
	}

	public static void setCurrentDay(Calendar currentDay) {
		Date.currentDay = currentDay;
	}

	public static void setLblDate(TextView lblDate) {
		Date.lblDate = lblDate;
	}

	public static void setTxtInfo(TextView txtInfo) {
		Date.txtInfo = txtInfo;
	}
	
	public static void setButtonToday(Button buttonToday) {
		Date.buttonToday = buttonToday;
	}
	
}
