package com.example.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

public class ContactsProvider extends ContentProvider {
	
	public static final Uri URI = Uri.parse("content://com.example.test.contacts");

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public boolean onCreate() {
		return false;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return query(
			uri, 
			projection, 
			selection, 
			selectionArgs, 
			sortOrder, 
			getContext().getResources().getInteger(R.integer.all_contacts)
		);
	}

	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int limit) {
		Cursor c = getContext().getContentResolver().query(
			Contacts.CONTENT_URI,
			new String[] { Contacts._ID, Contacts.DISPLAY_NAME },
			null,
			null,
			Contacts.DISPLAY_NAME + (limit > 0 ? " LIMIT " + limit : "")
		);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}

}