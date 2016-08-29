package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.theblackcub.builditbigger.backend.myApi.MyApi;
import com.theblackcub.jokedisplay.DisplayJokeActivity;

import java.io.IOException;

/**
 * Created by kare2436 on 7/24/16.
 */
public class EndPointTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static final String TAG = "EndPointTask";
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        context = params[0].first;
        String name = params[0].second;

        if (myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.api_url))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }



        try {
            Log.d(TAG, "doInBackground: " + context.getString(R.string.api_url));
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.setAction(context.getString(R.string.intent_joke_received));
        intent.putExtra(context.getString(R.string.joke_intent_extra), s);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
