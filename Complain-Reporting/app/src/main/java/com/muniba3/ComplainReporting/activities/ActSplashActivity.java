package com.muniba3.ComplainReporting.activities;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

import com.muniba3.ComplainReporting.R;

public class ActSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_splash);



        new Handler().postDelayed(new Runnable() {                            // Using handler with postDelayed called runnable run method
                                      // @Override
                                      public void run() {
                                          Intent i = new Intent(ActSplashActivity.this, LoginActivity.class);
                                          startActivity(i);                    // close this activity
                                          finish();
                                      }
                                  },
                4*500); // wait for 5 seconds
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


