package com.example.uogcafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class checked {
    public static String res;
    public checked(String Number) throws IOException {
        sendGET(Number);
    }
    private static void sendGET(String number) throws IOException {
        URL obj = new URL("HTTP://192.168.0.3/app_server/index.php?number='"+number+"'");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        // For POST only - START
        con.setDoOutput(true);
        // For POST only - END

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            res=response.toString();
        }
    }
}


















































































// programmed by yishak Terfe.....................