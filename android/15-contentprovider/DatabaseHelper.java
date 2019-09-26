package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseHelper(Context context, String name, int version){
		this(context, name, null, version);
	}
	
	public DatabaseHelper(Context context, String name) {
		this(context, name, VERSION);
	}
	

	// 在第一次创建数据库时候调用此函数(getWritableDatabase这种方法的时候)
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		System.out.println("create a database");
		db.execSQL("create table " + FirstContentProviderMetaData.UserTableMetaData.USERS_TABLE_NAME 
				+ "(" + FirstContentProviderMetaData.UserTableMetaData._ID 
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ FirstContentProviderMetaData.UserTableMetaData.USER_NAME
				+ " varchar(20));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
//		以下方法时触发此方法
//		DatabaseHelper databaseHelper = new DatabaseHelper(this, "mydatabase", 2);
//		database = databaseHelper.getWritableDatabase();

	}

}
