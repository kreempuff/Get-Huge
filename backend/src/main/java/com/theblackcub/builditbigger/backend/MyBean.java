package com.theblackcub.builditbigger.backend;

import com.theblackcub.JokeProvider;

import java.util.ArrayList;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;
    private JokeProvider jokeProvider= null;

    public MyBean() {
    }

    public String getData() {
        if (jokeProvider == null) {
            jokeProvider = new JokeProvider();
        }
        int random = (int) Math.floor(Math.random() * jokeProvider.getTotalJokes());
        return jokeProvider.provideJoke(random);
    }
}