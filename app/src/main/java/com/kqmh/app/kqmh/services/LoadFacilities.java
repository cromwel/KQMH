package com.kqmh.app.kqmh.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class LoadFacilities extends IntentService {
    public LoadFacilities() {
        super(LoadFacilities.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        loadJSONLocation();
    }


    public void loadJSONLocation() {

        String json;

        try {

            InputStream is = getAssets().open("allOrgUnits.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("organisationUnits").toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataObj = jsonArray.getJSONObject(i);

                String facility = dataObj.getString("name");
                String facilityID = dataObj.getString("id");
                JSONObject objectWard = dataObj.getJSONObject("parent");

                String ward = objectWard.getString("name");
                String wardID = objectWard.getString("id");
                JSONObject objectSubCounty = objectWard.getJSONObject("parent");

                String subCounty = objectSubCounty.getString("name");
                String subCountyID = objectSubCounty.getString("id");
                JSONObject objectCounty = objectSubCounty.getJSONObject("parent");

                String county = objectCounty.getString("name");
                String countyID = objectCounty.getString("id");

                Log.d("Facililty",facilityID+" "+facility);
                Log.d("Ward", wardID+" "+ward);
                Log.d("SubCounty", subCountyID+" "+subCounty);
                Log.d("County", countyID+" "+county);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
