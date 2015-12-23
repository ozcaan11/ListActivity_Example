package com.ozcaan11.listactivity_example;

import com.parse.Parse;

public class ForParseInit extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this);

        /// You must add
        /// android:name=".ForParseInit"
        /// to AnroidManifest.xml
    }
}
