package com.example.filehandlingdemo.notifications.business;


import com.example.filehandlingdemo.notifications.entities.Notification;
import com.example.filehandlingdemo.utils.AESUtil;
import com.example.filehandlingdemo.utils.Headings;
import com.example.filehandlingdemo.utils.NotificationBody;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Create by Weslei Dias.
 **/
@Slf4j
@Service

public class PushNotificationServiceImpl implements PushNotificationService {

    public static final String REST_API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkcWM1N20iLCJyb2xlcyI6W10sImlzcyI6Ii9hcGkvbG9naW4iLCJleHAiOjE3NTA1MTM1Mjl9.elXEZSl_4Nwb7sHBL38dosU8rduirOxV9ho-mcm4-mE";
    public static final String APP_ID = "4cedd394-1573-41bb-8764-c557825222d8";

    public void sendMessageToAllUsers(String message) {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic " + REST_API_KEY);//REST API
            con.setRequestMethod("POST");

            String strJsonBody = "{" + "\"app_id\": \"" + APP_ID + "\"," + "\"included_segments\": [\"All\"]," + "\"data\": {\"foo\": \"bar\"}," + "\"contents\": {\"en\": \"" + message + "\"}" + "}";


            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            jsonResponse = mountResponseRequest(con, httpResponse);
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void sendMessageToUser(String message, String userId, String data) {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", REST_API_KEY);
            con.setRequestMethod("POST");

            String strJsonBody = "{" + "\"app_id\": \"" + APP_ID + "\"," + "\"include_player_ids\": [\"" + userId + "\"],"
//          +  "\"include_external_user_ids\": [\""+ userId +"\"],"
                    + "\"data\": " + data + "," + "\"contents\": {\"en\": \"" + message + "\"}" + "}";


            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            jsonResponse = mountResponseRequest(con, httpResponse);
            System.out.println("jsonResponse:\n" + jsonResponse);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private String mountResponseRequest(HttpURLConnection con, int httpResponse) throws IOException {
        String jsonResponse;
        if (httpResponse >= HttpURLConnection.HTTP_OK && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
        } else {
            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
        }
        return jsonResponse;
    }

    @Override
    public void notifyMultiUsers(Notification notification) {
        try {
            String jsonResponse;

            URL url = new URL("http://88.99.87.18:8080/orderstage/api/pushnotification/onesignalProxy");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", REST_API_KEY);
            con.setRequestMethod("POST");

            /*String strJsonBody = "{" + "\"app_id\": \"" + APP_ID + "\","
                    + "\"include_player_ids\": [" + notification.getUserIds() + "],"
//          +  "\"include_external_user_ids\": [\""+ userId +"\"],"
                    + "\"headings\": {\"en\": \"" + notification.getTitle() + "\"},"
                    + "\"data\": " + notification.getPayload() + ","
                    + "\"contents\": {\"en\": \"" + notification.getMsg() + "\"}"
                    + "}";*/

            String jsonPayload = new Gson().toJson(notification.getPayload());

            SecretKey key = AESUtil.generateKey(256);

            IvParameterSpec Iv = AESUtil.generateIv();
            String cipherText = AESUtil.encrypt("AES/CBC/PKCS5Padding", jsonPayload, key, Iv);
            System.out.println("the cipher text is : {} " + cipherText);

            Headings headings = new Headings();
            headings.setEn(notification.getTitle());

            Map<String, String> contents = new HashMap<>();
            contents.put("en", notification.getMsg());

            NotificationBody notificationBody = NotificationBody.builder().
                    app_id(APP_ID).include_player_ids(notification.getUserIds()).
                    headings(headings).
                    data(Map.of("payload",
                            cipherText))
                    .contents(contents).
                    build();

            log.info("notif {} ", notificationBody);
            Gson gson = new Gson();


//            '{\"app_id\": \"4cedd394-1573-41bb-8764-c557825222d8\",\"include_player_ids\": [\"f1aae7f6-2630-42b6-bf15-b44133bdc851\",\"f1aae7f6-2630-42b6-bf15-b44133bdc854\"],\"headings\": {\"en\": \"request ???\"},\"data\": pickup request,\"contents\": {\"en\": \"???? ??? ???? you have a new request\"}}"}
            String data = gson.toJson(notificationBody).replace("\u003d", "=");

//            data.addProperty("body", notif);


//            log.info("strJsonBody:{}", strJsonBody);
            log.info("data:{}", data);

            byte[] sendBytes = data/* strJsonBody*/.getBytes(StandardCharsets.UTF_8);
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            jsonResponse = mountResponseRequest(con, httpResponse);
            System.out.println("jsonResponse:\n" + jsonResponse);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
