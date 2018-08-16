package com.kqmh.app.kqmh.Utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import com.kqmh.app.kqmh.Models.DataElement;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.io.OutputStreamWriter;
import java.util.List;

public class ExportToJSON {

    public static JSONObject buildJSON(String orgUnit) {
        JSONObject jsonDatavalueset = new JSONObject();
        try {
            jsonDatavalueset.put("dataSet", "TA4FU3zu93l");
            jsonDatavalueset.put("completeDate", "2018-02-02");
            jsonDatavalueset.put("period","201801");
            jsonDatavalueset.put("orgUnit", orgUnit);

            Gson gson = new Gson();
            List<DataElement> elementList = SQLite.select()
                    .from(DataElement.class)
                    .queryList();
            String elements = gson.toJson(elementList);
            JSONArray datavals = new JSONArray(elements);
            jsonDatavalueset.put("dataValues", datavals);
            Log.d("Json data", jsonDatavalueset.toString());

        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }

        return jsonDatavalueset;
    }

    public static void ExportToFile(JSONObject jsonDatavalueset, Context context){
        Log.d("ExportToJSON", "ExportToFile: " + jsonDatavalueset.toString());
        writeToFile("datavalueset.json", jsonDatavalueset.toString(), context);
    }

    private static void writeToFile(String filename,String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



}

