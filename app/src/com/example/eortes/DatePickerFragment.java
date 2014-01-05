package com.example.eortes;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	
	private Date dt;
	
	public DatePickerFragment() {
		super();
	}
	
	public DatePickerFragment(Date dt) {
		super();
		this.dt = dt;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker	
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Calendar d = Calendar.getInstance();
		d.set(Calendar.DATE, day);
		d.set(Calendar.MONTH, month);
		d.set(Calendar.YEAR, year);	
		dt.setCurrentDay(d);
		dt.refresh();		
	}
	
}
