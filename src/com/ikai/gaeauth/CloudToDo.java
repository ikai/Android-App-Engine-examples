package com.ikai.gaeauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CloudToDo extends Activity {
	
	Button loginButton;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginButton = (Button) findViewById(R.id.loginButton);
        
        loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CloudToDo.this, AccountList.class);
				startActivity(intent);
			}
		});
        
    }
}