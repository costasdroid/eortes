package com.example.eortes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "data";
    private static final int DATABASE_VERSION = 1;
    
    public MyDatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            
            // you can use an alternate constructor to specify a database location 
            // (such as a folder on the sd card)
            // you must ensure that this folder is available and you have permission
            // to write to it
            //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
            
    }
	
	public Cursor getThisDate(int date, int month) {
		SQLiteDatabase sql = getReadableDatabase();
		String[] args = {date + "", month + ""};
		 
		
		Cursor c = sql.rawQuery("Select col_3 From eort Where (col_1=? and col_2=?)", args);
		c.moveToFirst();
		return c;
	}
	
	
	public Cursor getAll() {
		SQLiteDatabase sql = getReadableDatabase();
		Cursor c = sql.rawQuery("Select col_3 From eort", null);
		c.moveToFirst();
		return c;
	}
}

