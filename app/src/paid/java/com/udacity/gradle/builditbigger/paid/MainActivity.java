package com.udacity.gradle.builditbigger.paid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.EndPointTask;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity {
    private MainActivityReceiver mainActivityReceiver;
    private View button;
    private View loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingProgress = findViewById(R.id.loading);
    }


    @Override
    protected void onResume() {
        if (button != null && button.getVisibility() == View.INVISIBLE) {
            button.setVisibility(View.VISIBLE);
            loadingProgress.setVisibility(View.GONE);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.intent_joke_received));
        if (mainActivityReceiver == null) {
            mainActivityReceiver = new MainActivityReceiver();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(mainActivityReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mainActivityReceiver);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) throws InterruptedException {
        if (this.button == null) {
            this.button = view;
        }
        this.button.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);
        new EndPointTask().execute(new Pair<Context, String>(this, "string"));
    }

    public class MainActivityReceiver extends BroadcastReceiver {

        private String TAG = "MainActivityReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
//            Log.i(TAG, "onReceive: ");
//            startActivity(intent);
        }
    }

}
