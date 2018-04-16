package com.theagobueno.mmaqapp;


import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MMaApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
