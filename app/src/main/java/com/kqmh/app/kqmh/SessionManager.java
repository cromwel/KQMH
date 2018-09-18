package com.kqmh.app.kqmh;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SessionManager {
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;
    private static final int PRIVATE_MODE = 0;

    private Context mContext;
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_BEARER_TOKEN = "token";
    private static final String KEY_IS_LOGGED_IN = "IsLoggedIn";
    private static final String DATE_TOKEN_REFRESHED = "TokenRefreshed";
    private static final String KEY_REFRESH_TOKEN = "refreshToken";
    private static final String USER_NAME = "userName";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_SAVED_OPTIONS = "isOptionsSaved";
    private static final String KEY_SAVED_OPTIONS2 = "isOptionsSaved2";
    private static final String KEY_SAVED_OPTIONS3 = "isOptionsSaved3";
    private static final String KEY_SAVED_OPTIONS4 = "isOptionsSaved4";
    private static final String KEY_SAVED_OPTIONS5 = "isOptionsSaved5";
    private static final String KEY_SAVED_OPTIONS6 = "isOptionsSaved6";
    private static final String KEY_SAVED_OPTIONS7 = "isOptionsSaved7";
    private static final String KEY_SAVED_OPTIONS8 = "isOptionsSaved8";
    private static final String KEY_SAVED_OPTIONS9 = "isOptionsSaved9";
    private static final String KEY_SAVED_OPTIONS10 = "isOptionsSaved10";
    private static final String KEY_SAVED_OPTIONS11_1 = "isOptionsSaved11_1";
    private static final String KEY_SAVED_OPTIONS11_2 = "isOptionsSaved11_2";
    private static final String KEY_SAVED_OPTIONS11_3 = "isOptionsSaved11_3";
    private static final String KEY_SAVED_OPTIONS11_4 = "isOptionsSaved11_4";
    private static final String KEY_SAVED_OPTIONS11_5 = "isOptionsSaved11_5";
    private static final String KEY_SAVED_OPTIONS11_6 = "isOptionsSaved11_6";
    private static final String KEY_SAVED_OPTIONS11_7 = "isOptionsSaved11_7";
    private static final String KEY_SAVED_OPTIONS11_8 = "isOptionsSaved11_8";
    private static final String KEY_SAVED_OPTIONS11_9 = "isOptionsSaved11_9";
    private static final String KEY_SAVED_OPTIONS11_10 = "isOptionsSaved11_10";
    private static final String KEY_SAVED_OPTIONS11_11 = "isOptionsSaved11_11";
    private static final String KEY_SAVED_OPTIONS11_12 = "isOptionsSaved11_12";
    private static final String KEY_SAVED_OPTIONS11_13 = "isOptionsSaved11_13";
    private static final String KEY_SAVED_OPTIONS11_14 = "isOptionsSaved11_14";
    private static final String KEY_SAVED_OPTIONS12 = "isOptionsSaved12";


    public SessionManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void setKeyBearerToken(String token) {
        pref.edit().putString(KEY_BEARER_TOKEN, token).apply();
    }

    public String getKeyBearerToken() {

        return pref.getString(KEY_BEARER_TOKEN, null);
    }

    public void setKeyRefreshToken(String token) {
        pref.edit().putString(KEY_REFRESH_TOKEN, token).apply();
    }

    public String getKeyRefreshToken() {

        return pref.getString(KEY_REFRESH_TOKEN, null);
    }

    public boolean isLoggedIn() {

        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean loggedIn) {
        pref.edit().putBoolean(KEY_IS_LOGGED_IN, loggedIn).apply();
    }

    public void setDateTokenRefreshed(String date) {
        pref.edit().putString(DATE_TOKEN_REFRESHED, date).apply();
    }

    public String getDateTokenRefreshed() {

        return pref.getString(DATE_TOKEN_REFRESHED, null);
    }

    public void setUserName(String token) {

        pref.edit().putString(USER_NAME, token).apply();
    }

    public String getUserName() {

        return pref.getString(USER_NAME, null);
    }

    public void setKeyPassword(String token) {

        pref.edit().putString(KEY_PASSWORD, token).apply();
    }

    public String getPassword() {

        return pref.getString(KEY_PASSWORD, null);
    }

    public static String getKeyUserId() {
        return KEY_USER_ID;
    }

    public void setKeyUserId(String id) {

        pref.edit().putString(KEY_USER_ID, id).apply();
    }

    public boolean isOptionsSaved() {
        return pref.getBoolean(KEY_SAVED_OPTIONS, false);
    }

    public boolean isOptionsSaved2() {
        return pref.getBoolean(KEY_SAVED_OPTIONS2, false);
    }

    public boolean isOptionsSaved3() {
        return pref.getBoolean(KEY_SAVED_OPTIONS3, false);
    }

    public boolean isOptionsSaved4() {
        return pref.getBoolean(KEY_SAVED_OPTIONS4, false);
    }

    public boolean isOptionsSaved5() {
        return pref.getBoolean(KEY_SAVED_OPTIONS5, false);
    }

    public boolean isOptionsSaved6() {
        return pref.getBoolean(KEY_SAVED_OPTIONS6, false);
    }

    public boolean isOptionsSaved7() {
        return pref.getBoolean(KEY_SAVED_OPTIONS7, false);
    }

    public boolean isOptionsSaved8() {
        return pref.getBoolean(KEY_SAVED_OPTIONS8, false);
    }

    public boolean isOptionsSaved9() {
        return pref.getBoolean(KEY_SAVED_OPTIONS9, false);
    }

    public boolean isOptionsSaved10() {
        return pref.getBoolean(KEY_SAVED_OPTIONS10, false);
    }

    public boolean isOptionsSaved11_1() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_1, false);
    }

    public boolean isOptionsSaved11_2() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_2, false);
    }

    public boolean isOptionsSaved11_3() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_3, false);
    }

    public boolean isOptionsSaved11_4() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_4, false);
    }

    public boolean isOptionsSaved11_5() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_5, false);
    }

    public boolean isOptionsSaved11_6() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_6, false);
    }

    public boolean isOptionsSaved11_7() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_7, false);
    }

    public boolean isOptionsSaved11_8() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_8, false);
    }

    public boolean isOptionsSaved11_9() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_9, false);
    }

    public boolean isOptionsSaved11_10() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_10, false);
    }

    public boolean isOptionsSaved11_11() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_11, false);
    }

    public boolean isOptionsSaved11_12() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_12, false);
    }

    public boolean isOptionsSaved11_13() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_13, false);
    }

    public boolean isOptionsSaved11_14() {
        return pref.getBoolean(KEY_SAVED_OPTIONS11_14, false);
    }

    public boolean isOptionsSaved12() {
        return pref.getBoolean(KEY_SAVED_OPTIONS12, false);
    }

    public void setKeySavedOptions(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS,isSaved).apply();
    }

    public void setKeySavedOptions2(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS2,isSaved).apply();
    }

    public void setKeySavedOptions3(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS3,isSaved).apply();
    }

    public void setKeySavedOptions4(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS4,isSaved).apply();
    }

    public void setKeySavedOptions5(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS5,isSaved).apply();
    }

    public void setKeySavedOptions6(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS6,isSaved).apply();
    }

    public void setKeySavedOptions7(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS7,isSaved).apply();
    }

    public void setKeySavedOptions8(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS8,isSaved).apply();
    }

    public void setKeySavedOptions9(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS9,isSaved).apply();
    }

    public void setKeySavedOptions10(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS10,isSaved).apply();
    }

    public void setKeySavedOptions11_1(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_1,isSaved).apply();
    }

    public void setKeySavedOptions11_2(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_2,isSaved).apply();
    }

    public void setKeySavedOptions11_3(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_3,isSaved).apply();
    }

    public void setKeySavedOptions11_4(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_4,isSaved).apply();
    }

    public void setKeySavedOptions11_5(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_5,isSaved).apply();
    }

    public void setKeySavedOptions11_6(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_6,isSaved).apply();
    }

    public void setKeySavedOptions11_7(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_7,isSaved).apply();
    }

    public void setKeySavedOptions11_8(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_8,isSaved).apply();
    }

    public void setKeySavedOptions11_9(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_9,isSaved).apply();
    }

    public void setKeySavedOptions11_10(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_10,isSaved).apply();
    }

    public void setKeySavedOptions11_11(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_11,isSaved).apply();
    }

    public void setKeySavedOptions11_12(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_13,isSaved).apply();
    }

    public void setKeySavedOptions11_13(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_13,isSaved).apply();
    }

    public void setKeySavedOptions11_14(boolean isSaved) {
        pref.edit().putBoolean(KEY_SAVED_OPTIONS11_14,isSaved).apply();
    }

}

