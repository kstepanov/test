package com.example.test;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.SimpleCursorAdapter;

public class ContactsAdapter extends SimpleCursorAdapter {

	public ContactsAdapter(Context context, Cursor c) {
		super(
			context,
			android.R.layout.simple_list_item_1,
			c,
			new String[] { Contacts.DISPLAY_NAME },
			new int[] { android.R.id.text1 },
			0
		);
	}

}