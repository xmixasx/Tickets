package com.tickets.mgolimbiiev.tickets.Tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by Миха on 19.03.2016.
 */
public class TaskToGetToken implements Callable<String> {

    @Override
    public String call() throws Exception {
        URL url = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            url = new URL("http://booking.uz.gov.ua/");
            connection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } {

        }

        return sb.toString();
    }
}
