package com.migs.wowtoken;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 5/21/2017.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){
    }

    public static List<WowToken> fetchWowTokenData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<WowToken> tokens = extractDataFromJson(jsonResponse);

        return tokens;
    }

    private static List<WowToken> extractDataFromJson(String tokenJSON){
        if (TextUtils.isEmpty(tokenJSON))
            return null;

        List<WowToken> tokens = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(tokenJSON);

            /* NA Token */
            JSONObject northAmerica = baseJsonResponse.getJSONObject("NA");

            //Get Date
            JSONObject raw = northAmerica.getJSONObject("raw");
            long lastUpdate = raw.getLong("updated");

            //Get current, high, and low price
            JSONObject formatted = northAmerica.getJSONObject("formatted");
            String currentPrice = formatted.getString("buy");
            String priceHigh = formatted.getString("24max");
            String priceLow = formatted.getString("24min");

            WowToken naToken = new WowToken("North America", currentPrice, priceHigh, priceLow, lastUpdate);

            tokens.add(naToken);

            /* EU Token */
            JSONObject europe = baseJsonResponse.getJSONObject("EU");

            //Get Date
            raw = europe.getJSONObject("raw");
            lastUpdate = raw.getLong("updated");

            //Get current, high, and low price
            formatted = europe.getJSONObject("formatted");
            currentPrice = formatted.getString("buy");
            priceHigh = formatted.getString("24max");
            priceLow = formatted.getString("24min");

            WowToken euToken = new WowToken("Europe", currentPrice, priceHigh, priceLow, lastUpdate);

            tokens.add(euToken);

            /* CN Token */
            JSONObject china = baseJsonResponse.getJSONObject("CN");

            //Get Date
            raw = china.getJSONObject("raw");
            lastUpdate = raw.getLong("updated");

            //Get current, high, and low price
            formatted = china.getJSONObject("formatted");
            currentPrice = formatted.getString("buy");
            priceHigh = formatted.getString("24max");
            priceLow = formatted.getString("24min");

            WowToken cnToken = new WowToken("China", currentPrice, priceHigh, priceLow, lastUpdate);

            tokens.add(cnToken);

            /* TW Token */
            JSONObject taiwan = baseJsonResponse.getJSONObject("TW");

            //Get Date
            raw = taiwan.getJSONObject("raw");
            lastUpdate = raw.getLong("updated");

            //Get current, high, and low price
            formatted = taiwan.getJSONObject("formatted");
            currentPrice = formatted.getString("buy");
            priceHigh = formatted.getString("24max");
            priceLow = formatted.getString("24min");

            WowToken twToken = new WowToken("Taiwan", currentPrice, priceHigh, priceLow, lastUpdate);

            tokens.add(twToken);

            /* KR Token */
            JSONObject korea = baseJsonResponse.getJSONObject("KR");

            //Get Date
            raw = korea.getJSONObject("raw");
            lastUpdate = raw.getLong("updated");

            //Get current, high, and low price
            formatted = korea.getJSONObject("formatted");
            currentPrice = formatted.getString("buy");
            priceHigh = formatted.getString("24max");
            priceLow = formatted.getString("24min");

            WowToken krToken = new WowToken("Korea", currentPrice, priceHigh, priceLow, lastUpdate);

            tokens.add(krToken);
        }
        catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the WoW token JSON results", e);
        }

        return tokens;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem building url ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        if(url == null)
            return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        }
        finally {
            if(urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            try {
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return output.toString();
    }
}
