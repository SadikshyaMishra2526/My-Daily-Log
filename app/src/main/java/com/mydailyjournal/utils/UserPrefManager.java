package com.mydailyjournal.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPrefManager {
    private SharedPreferences userPref;
    private SharedPreferences.Editor userPrefEditor;
    private Context mContext;

    // shared pref mode
    private static final int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREF_NAME = "com-mydailyjournal-shared-pref";

    private static final String FULL_NAME = "full_name";
     private static final String LOGGED_IN_DATE = "logged_in_date";

    public UserPrefManager(Context context) {
        this.mContext = context;
        userPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        userPrefEditor = userPref.edit();
    }

    public void setLoggedInLoggerName(String userName) {
        userPrefEditor.putString(FULL_NAME,userName);
        userPrefEditor.apply();
    }

    public String getLoggedInLoggerName() {
        return userPref.getString(FULL_NAME, null);
    }


    public void setLoggedInDate(String loggedInDate) {
        userPrefEditor.putString(LOGGED_IN_DATE,loggedInDate);
        userPrefEditor.apply();

    }


    public String getLoggedInDate() {
        return userPref.getString(LOGGED_IN_DATE, null);
    }


    public void clearData() {
        userPrefEditor = userPref.edit();
        // Clearing all data from Shared Preferences
        userPrefEditor.clear();
        userPrefEditor.apply();

    }

}


