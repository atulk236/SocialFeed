package com.ueniweb.techsuperficial.socialmerevup.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;

public class GsonConvertor {
    public static Gson buildGSONConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
      //  gsonBuilder.registerTypeAdapter(DateTime.class, new DateDeserializer());
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateSerializer());
        gsonBuilder.setDateFormat(DateFormat.LONG);
        return gsonBuilder.create();
    }



    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

}
