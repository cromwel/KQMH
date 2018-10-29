package com.kqmh.app.kqmh.Utils;

import android.util.Base64;
import android.util.Log;


public class AuthBuilder {
    private static String username;
    private static String password;


    public AuthBuilder(String username, String password) {
        AuthBuilder.username = username;
        AuthBuilder.password = password;
    }

    public static String encode(String username, String password) throws Exception {
        String encodeValue = username + ":" + password;
        byte[] encoded = Base64.encode(encodeValue.getBytes(), Base64.DEFAULT);
        String auth = "Basic " + new String(encoded);

        Log.d("encoded", auth);

        return auth;
    }


}
