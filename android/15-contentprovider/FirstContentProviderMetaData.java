package com.example.test;

import android.net.Uri;
import android.provider.BaseColumns;

public class FirstContentProviderMetaData {

	
	public static final String AUTHORITY = "com.example.test.FirstContentProvider";
	public static final String DATABASE_NAME = "FirstContentProvider.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final class UserTableMetaData implements BaseColumns{
		public static final String USERS_TABLE_NAME = "users";
		// content provider's URI
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+USERS_TABLE_NAME);
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.firstprovider.user";
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";
		// column of table
		public static final String USER_NAME = "name";
		public static final String DEFAULT_SORT_ORDER = "_id desc";

		
		
	}

}
