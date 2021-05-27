package com.example.mealplannerapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Reference - https://medium.com/@JasonCromer/android-asynctask-http-request-tutorial-6b429d833e28

public class GetFoodProduct extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD_TYPE = "GET";
    public static final int READ_TIMEOUT_SECONDS = 15000; //15 SECONDS
    public static final int CONNECTION_TIMEOUT_SECONDS = 15000; //15 SECONDS
    @Override
    protected String doInBackground(String... params){ //used for background computation that can take a long time.

        String stringUrl = params[0];
        String result;
        String inputLine;

        try {
            //Create a URL object to hold the URL

            URL holdUrl = new URL(stringUrl);

            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    holdUrl.openConnection();

            //Set the  methods and connection timeouts
            connection.setRequestMethod(REQUEST_METHOD_TYPE);
            connection.setReadTimeout(READ_TIMEOUT_SECONDS);
            connection.setConnectTimeout(CONNECTION_TIMEOUT_SECONDS);

            //make the connection to url
            connection.connect();

            //Create a new InputStreamReader used to read input from API
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");

            //Create a new buffered reader to read the text from an Input stream and String Builder to build up a mutable string of characters

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();


            //Check if the line we are reading is not null
            //reading input, parsing and append to string builder
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }


            //Close InputStream and Buffered reader
            reader.close();
            streamReader.close();


            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        }
        catch(IOException e){ // only need ioexception for GET request in case something wrong in input or output
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    protected void onPostExecute(String result){ // can be used to display progress in the user interface
        super.onPostExecute(result);
    }
}
