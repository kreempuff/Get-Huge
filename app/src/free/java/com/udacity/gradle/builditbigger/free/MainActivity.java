package com.udacity.gradle.builditbigger.free;

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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndPointTask;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity {
    private MainActivityReceiver mainActivityReceiver;
    private View button;
    private View loadingProgress;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingProgress = findViewById(R.id.loading);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                getJoke();
            }

        });

        requestNewInterstitial();
    }


    @Override
    protected void onResume() {
        super.onResume();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mainActivityReceiver);
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

    public void tellJoke(View view) {
        if (this.button == null) {
            this.button = view;
        }
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            getJoke();
        }

    }

    public class MainActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            startActivity(intent);
        }
    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void getJoke() {
        this.button.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.VISIBLE);
        new EndPointTask().execute(this);
    }

}
