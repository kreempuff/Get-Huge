package com.theblackcub.builditbigger.backend;

import java.util.ArrayList;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;
    private String[] jokes = {"Do you know any good chemistry jokes? NaBro",
            "Why did the chicken cross the road? To get to the other side",
            "Your social life"};

    public MyBean() {
    }

    public String getData() {
        int random = (int) Math.floor(Math.random() * jokes.length);
        return jokes[random];
    }

    public void setData(String data) {
        myData = data;
    }
}