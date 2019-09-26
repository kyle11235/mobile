package com.example.test;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.test.FirstContentProviderMetaData.UserTableMetaData;

public class FirstContentProvider extends ContentProvider {

	// 可参考文档关于content provider的实例
	public static final UriMatcher uriMatcher;
	public static final int USERS = 1;
	public static final int USERS_ID = 2;

	static {
		// 用来验证访问者的URI是否正确
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(FirstContentProviderMetaData.AUTHORITY, "users", USERS);
		uriMatcher.addURI(FirstContentProviderMetaData.AUTHORITY, "users/#", USERS_ID);
	}

	// 起别名
	public static Map<String, String> userProjectionMap;
	static {
		userProjectionMap = new HashMap<String, String>();
		userProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}

	private DatabaseHelper databaseHelper;

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		System.out.println("getType");

		switch (uriMatcher.match(uri)) {
		case USERS:
			return UserTableMetaData.CONTENT_TYPE;
		case USERS_ID:
			return UserTableMetaData.CONTENT_TYPE_ITEM;

		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}

	}

	@Override
	public boolean onCreate() {
		databaseHelper = new DatabaseHelper(getContext(), FirstContentProviderMetaData.DATABASE_NAME);
		System.out.println("onCreate");
		return false;
	}

	// content provider 主要用于应用之间共享数据
	// 以下接口的具体实现随意，可以用database，xml，文件等

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		System.out.println("query");

		SQLiteQueryBuilder sb = new SQLiteQueryBuilder();
		
		switch (uriMatcher.match(uri)) {
		case USERS:
			sb.setTables(UserTableMetaData.USERS_TABLE_NAME);
			sb.setProjectionMap(userProjectionMap);
			break;
			
		case USERS_ID:
			sb.setTables(UserTableMetaData.USERS_TABLE_NAME);
			sb.setProjectionMap(userProjectionMap);
			sb.appendWhere(UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));
			break;
		}
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = UserTableMetaData.DEFAULT_SORT_ORDER;
		}else{
			orderBy = sortOrder;
		}
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor cursor = sb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		System.out.println("insert");

		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		long rowId = db.insert(UserTableMetaData.USERS_TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertUri = ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertUri, null);
			return insertUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		System.out.println("delete");
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
