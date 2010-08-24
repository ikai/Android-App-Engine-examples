package com.ikai.gaeauth.async.callbacks;

import java.io.IOException;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ikai.gaeauth.async.GetCookieTask;

public class GetAuthTokenCallback implements AccountManagerCallback<Bundle> {
	Context context;
	
	public GetAuthTokenCallback(Context _context) {
		context = _context;
	}
	
	public void run(AccountManagerFuture<Bundle> result) {
		Bundle bundle;
		try {
			bundle = result.getResult();
			Intent intent = (Intent) bundle.get(AccountManager.KEY_INTENT);
			if (intent != null) {
				context.startActivity(intent);
			} else {
				onGetAuthToken(bundle);
			}
		} catch (OperationCanceledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void onGetAuthToken(Bundle bundle) {
		String auth_token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
		new GetCookieTask(context).execute(auth_token);
	}
};
