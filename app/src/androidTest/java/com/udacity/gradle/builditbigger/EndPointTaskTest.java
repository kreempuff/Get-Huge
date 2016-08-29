package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.LocalBroadcastManager;
import android.test.mock.MockContext;
import android.util.Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by kare2436 on 8/28/16.
 */
@RunWith(AndroidJUnit4.class)
public class EndPointTaskTest {
    MockContext mContext;
    TestReceiver testReceiver;

    @Before
    public void getLocalBroadcast() {
        testReceiver = new TestReceiver();
        mContext = new MockContext();
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction("com.udacity.gradle.builditbigger.JOKE_RECEIVED");
        //mContext.registerReceiver(testReceiver, intentFilter);
    }

//    @After
//    public void removeBroadcast() {
//        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(testReceiver);
//    }

    @Test
    public void testOnPostExecute() throws Exception {
        new EndPointTask().execute(new Pair<Context, String>(mContext, "string"));
    }


    public class TestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            assert intent.getStringExtra("com.udacity.gradle.builditbigger.joke") != null;
        }
    }
}