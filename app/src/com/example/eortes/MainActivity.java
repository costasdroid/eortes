package com.example.eortes;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Date date = new Date();
	
	private SwipeListener listen = new SwipeListener();
	private GestureDetector detect = new GestureDetector(getBaseContext(),
			listen);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Date.setLblDate((TextView) findViewById(R.id.labelDate));
		Date.setTxtInfo((TextView) findViewById(R.id.textDay));
		Date.setButtonToday((Button) findViewById(R.id.button1));

		date.setDb(this);
		
		date.refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);		
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean moved = detect.onTouchEvent(event);
		if (moved) date.add(listen.getDirection());
		return moved;
	}
	
	public void setToday(View view) {	
		date.reset();
	}
	
	public void goToDay(View view) {
	    DialogFragment newFragment = new DatePickerFragment(date);
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
}
