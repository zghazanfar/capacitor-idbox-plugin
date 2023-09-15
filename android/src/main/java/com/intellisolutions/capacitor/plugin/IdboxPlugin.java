package com.intellisolutions.capacitor.plugin;

import android.util.Log;

public class IdboxPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
//    public String registerRequest(any response) {
//        Log.i("register", response);
//        return response;
//    }
}
