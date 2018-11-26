
package com.example.android.datafrominternet.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    final static String WEATHER_BASE_URL =
            "https://api.openweathermap.org/data/2.5/weather";

    final static String PARAM_QUERY = "q";
    final static String Place="Tunis ,tn";
    final static String Appid="9286fd86071c32c41b2770336d782fcf";
    final static String units = "metric";
    final static String UNITS_PARAM = "units";
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";
    final static String APPID="APPID";

    public static URL buildUrl(String weatherSearchQuery) {
        Uri builtUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(APPID,Appid)
                .appendQueryParameter(UNITS_PARAM,units)
                .appendQueryParameter(PARAM_QUERY, weatherSearchQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}