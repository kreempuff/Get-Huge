package com.theblackcub.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {
    private String joke;
    private TextView jokeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        jokeDisplay = (TextView) findViewById(R.id.joke_text_view);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.text_fade_in);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            joke = extras.getString(getResources().getString(R.string.joke_intent_extra));
            if (jokeDisplay != null) {
                jokeDisplay.setText(joke);
                jokeDisplay.clearAnimation();
                jokeDisplay.startAnimation(animation);
            }
        }
    }
}
