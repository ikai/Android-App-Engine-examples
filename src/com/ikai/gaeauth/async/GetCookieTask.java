package com.ikai.gaeauth.async;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;

public class GetCookieTask extends AsyncTask<String, Void, Boolean> {
	DefaultHttpClient http_client = new DefaultHttpClient();
	Context context;
	
	public GetCookieTask(Context _context) {
		context = _context;
	}
	
	protected Boolean doInBackground(String... tokens) {
		try {
			// Don't follow redirects
			http_client.getParams().setBooleanParameter(
					ClientPNames.HANDLE_REDIRECTS, false);

			HttpGet http_get = new HttpGet(
					"https://py-blobstore.appspot.com/_ah/login?continue=http://localhost/&auth="
							+ tokens[0]);
			HttpResponse response;
			response = http_client.execute(http_get);
			if (response.getStatusLine().getStatusCode() != 302)
				// Response should be a redirect
				return false;

			for (Cookie cookie : http_client.getCookieStore().getCookies()) {
				if (cookie.getName().equals("ACSID"))
					return true;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			http_client.getParams().setBooleanParameter(
					ClientPNames.HANDLE_REDIRECTS, true);
		}
		return false;
	}

	protected void onPostExecute(Boolean result) {
		new AuthenticatedRequestTask(context)
				.execute("http://py-blobstore.appspot.com/admin/");
	}

}
