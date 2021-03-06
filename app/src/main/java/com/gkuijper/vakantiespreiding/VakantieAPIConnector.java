package com.gkuijper.vakantiespreiding;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Gabrielle on 24-06-17.
 */

public class VakantieAPIConnector extends AsyncTask<String, Void, String> {

    //tag to use when information is logged, shows class name
    private static final String TAG = VakantieAPIConnector.class.getSimpleName();

    private VakantieAvailable listener = null;

    public VakantieAPIConnector(VakantieAvailable listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        //create string of bytes
        InputStream inputStream = null;
        //result we will return
        String response = "";
        int responseCode = -1;
        //create URL object with given URL as parameter in .execute()
        String productUrl = params[0];
        try {
            URL url = new URL(productUrl);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                return null;
            }
            //initialise HTTP connection
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");

            //request connection using given URL
            httpConn.connect();

            //check if succeeded
            responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
                response = getStringFromInputStream(inputStream);
            } else {
                Log.e(TAG, "Error, invalid response");
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground | MalformedURLException " + e.getLocalizedMessage());
        } catch (IOException e) {
            Log.e(TAG, "doInBackground | IOException" + e.getLocalizedMessage());
        }
        return response;
    }


    @Override
    protected void onPostExecute(String response) {
        //check if response is valid
        if (response == null || response == "") {
            Log.e(TAG, "onPostExecute received empty response" + response);
            return;
        }

        //JSON parsing
        JSONObject json;

        try {
            //entire JSON file in JSONObject
            json = new JSONObject(response);

            //get all items
            JSONArray items = json.getJSONArray("content");

            for (int i = 0; i < items.length(); i++) {

                JSONArray vacations = items.getJSONObject(i).getJSONArray("vacations");

                for (int v = 0; v < vacations.length(); v++) {
                    JSONObject vakantie = vacations.getJSONObject(v);
                    String naam = vakantie.optString("type");
                    boolean compulsory = vakantie.optBoolean("compulsorydates");
                    ArrayList<Tijdvak> tijden = new ArrayList<>();

                    JSONArray regions = vacations.getJSONObject(v).getJSONArray("regions");
                    for (int k = 0; k < regions.length(); k++) {
                        JSONObject region = regions.getJSONObject(k);
                        String regionName = region.optString("region");
                        String startDate = region.optString("startdate");
                        String endDate = region.optString("enddate");

                        String start = startDate.substring(0,10);
                        String end = endDate.substring(0,10);


                        Tijdvak vak = new Tijdvak(regionName, start, end);
                        tijden.add(vak);

                    }
                    VakantieItem vakantieItem = new VakantieItem(naam, compulsory, tijden);
                    listener.VakantieAvailable(vakantieItem);

                }
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }


    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    //call back interface
    public interface VakantieAvailable {
        void VakantieAvailable(VakantieItem vakantie);
    }
}
