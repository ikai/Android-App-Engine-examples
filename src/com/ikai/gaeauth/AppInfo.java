package com.ikai.gaeauth;

import org.apache.http.impl.client.DefaultHttpClient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ikai.gaeauth.async.callbacks.GetAuthTokenCallback;

public class AppInfo extends Activity {
	DefaultHttpClient http_client = new DefaultHttpClient();
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_info);
		textView = (TextView) findViewById(R.id.account_name);
		textView.setText("HERE IS SOME TEXT YARRR");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();
		AccountManager accountManager = AccountManager
				.get(getApplicationContext());
		Account account = (Account) intent.getExtras().get("account");

		accountManager.getAuthToken(account, "ah", false,
				new GetAuthTokenCallback(this), null);
	}
}
