package com.erikligai.doctorplzsaveme.backend;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.view.KeyEventDispatcher;
import android.widget.Toast;

// https://stackoverflow.com/questions/11411395/how-to-get-current-foreground-activity-context-in-android

public class Toaster {
    private static Toaster ourInstance = null;
    Context context = null;

    public static Toaster getInstance() {
        return ourInstance;
    }

    public Toaster(Context context) {
        ourInstance = this;
        this.context = context;
    }

    public void toastMessage(String message)
    {

    }
}
