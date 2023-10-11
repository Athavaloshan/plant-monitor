package com.android.plantmonitor.data;

import kotlin.jvm.Synchronized;

//This is a singleton class
public class DataManager {

    private static DataManager sManager;

    private DataManager(){

    }

    public static synchronized DataManager getInstance() {
        if (sManager == null) {
            sManager = new DataManager();
        }

        return sManager;
    }

}
