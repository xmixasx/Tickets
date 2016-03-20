package com.tickets.mgolimbiiev.tickets;

import android.app.Application;

/**
 * Created by Миха on 19.03.2016.
 */
public class myApplication extends Application {
    private static myApplication instance;

    public static myApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
