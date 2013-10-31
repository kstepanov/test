package com.example.test;

import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final String SPLASH_TAG = "SPLASH SCREEN";
	private final int SPLASH_SCREEN_DURATION = 3000;
	private View splashLayout;
	private ListView contactsListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
		contactsListView = (ListView)findViewById(R.id.contacts);
		splashLayout = findViewById(R.id.splashLayout);
		
		splashScreenDurationTimer.start();
		
		contactsListView.setAdapter(createContactsAdapter());
	}
	
	public ContactsAdapter createContactsAdapter() {
		return new ContactsAdapter(this, createContactsCursor());
	}
	
	public Cursor createContactsCursor() {
		return getContentResolver().query(
				ContactsProvider.URI,
				new String[] { Contacts._ID, Contacts.DISPLAY_NAME },
				null,
				null,
				Contacts.DISPLAY_NAME
			);
	}
	
	public void hideSplashView() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				splashLayout.setVisibility(View.GONE);
			}
		});
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
	
	private Thread splashScreenDurationTimer = new Thread() {
		public void run() {
			try {
				sleep(SPLASH_SCREEN_DURATION);
			} catch (InterruptedException exception) {
				Log.e(SPLASH_TAG, exception.getMessage());
			} finally {
				hideSplashView();
			}
		};
	};

}