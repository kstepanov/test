package com.example.test;

import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Cursor c = getContentResolver().query(
			ContactsProvider.URI,
			new String[] { Contacts._ID, Contacts.DISPLAY_NAME },
			null,
			null,
			Contacts.DISPLAY_NAME
		);
		ContactsAdapter ca = new ContactsAdapter(this, c);
		ListView contacts = (ListView)findViewById(R.id.contacts);
		contacts.setAdapter(ca);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.logout:
			finish();
			
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}