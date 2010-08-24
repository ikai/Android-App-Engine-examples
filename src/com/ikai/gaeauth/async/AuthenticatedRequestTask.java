package com.ikai.gaeauth.async;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class AuthenticatedRequestTask extends AsyncTask<String, Void, HttpResponse> {
	  
	DefaultHttpClient http_client = new DefaultHttpClient();
	Context context;
	
	public AuthenticatedRequestTask(Context _context) {
		context = _context;
	}
		
    protected HttpResponse doInBackground(String... urls) {
            try {
                    HttpGet http_get = new HttpGet(urls[0]);
                    return http_client.execute(http_get);
            } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return null;
    }
    
    protected void onPostExecute(HttpResponse result) {
            try {
                    // BufferedReader reader = new BufferedReader(new InputStreamReader(result.getEntity().getContent()));
                    // String first_line = reader.readLine();
                    Log.i(AuthenticatedRequestTask.class.getName(), "Received auth token");
                    // TODO: Make this work
                    Toast.makeText(context, "RECEIVED AUTH TOKEN", Toast.LENGTH_SHORT).show();                          
            } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }

}
