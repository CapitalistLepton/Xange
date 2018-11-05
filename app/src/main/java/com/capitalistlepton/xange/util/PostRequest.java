package com.capitalistlepton.xange.util;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Executes a POST request asynchronously to the specified URL with the given parameters.
 */
public class PostRequest extends AsyncTask<String, String, JSONObject> {

    /** Base URL of the CRUD API */
    private static final String BASE_URL = "";
    /** Parameters to send to the API */
    private String urlParameters;

    /**
     * Creates a new instance with the given URL parameters.
     *
     * @param urlParameters String parameters to send with POST request.
     */
    public PostRequest(String urlParameters) {
        this.urlParameters = urlParameters;
    }

    /**
     * Execute the actual POST request with the given URLs.
     * NOTE: Only the first URL is used.
     *
     * @param urls List of URLs. Only the first is used and the rest are ignored.
     * @return JSONObject of the response from the API.
     */
    @Override
    protected JSONObject doInBackground(String... urls) {
        try {
            // Make URL to send the request to
            URL url = new URL(BASE_URL + urls[0]);
            // Open up the connection to the URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(con.getOutputStream ());
            wr.writeBytes(urlParameters);
            wr.flush ();
            wr.close ();

            // Get response
            InputStream is = con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();

            // Parse response into a JSONObject
            JSONObject result = new JSONObject(response.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the result of the request.
     *
     * @param jsonObject JSONObject result of the request.
     */
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
