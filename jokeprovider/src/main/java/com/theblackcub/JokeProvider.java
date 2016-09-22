package com.theblackcub;

public class JokeProvider {
    private String[] jokes = {"Do you know any good chemistry jokes? NaBro",
            "Why did the chicken cross the road? To get to the other side",
            "Your social life"};

    public String provideJoke(int which) {
        return jokes[which];
    }

    public int getTotalJokes() {
        return jokes.length;
    }
}
