package com.dashapp.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import com.dashapp.controller.MainController;
import com.dashapp.view.NavigatorView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LoginService {

    private static boolean isLogged = false;
    private static int userId = -1;
    private static String userRole = null;

    private static final String LOGIN_URL = "http://3.123.253.157/api/login.php";

    public boolean isLogged() {
        return isLogged;
    }

    public String getUserRole() {
        return userRole;
    }

    public int getUserId() {
        return userId;
    }

    public CompletableFuture<Boolean> login(String email, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(LOGIN_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String postData = "email=" + encode(email) + "&password=" + encode(password);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(postData.getBytes(StandardCharsets.UTF_8));
                }

                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    System.err.println("Errore HTTP: " + responseCode);
                    return false;
                }

                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    Gson gson = new Gson();
                    JsonObject json = gson.fromJson(response.toString(), JsonObject.class);

                    boolean loggedIn = json.has("logged_in") && json.get("logged_in").getAsBoolean();
                    if (loggedIn) {
                        isLogged = true;
                        userId = json.has("id") ? json.get("id").getAsInt() : -1;
                        userRole = json.has("ruolo") ? json.get("ruolo").getAsString() : null;
                    } else {
                        isLogged = false;
                        userId = -1;
                        userRole = null;
                    }
                    return loggedIn;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public static void logOut(){
        isLogged = false;
        userId = -1;
        userRole = null;
        MainController mc = NavigatorView.getMainController();
        mc.nascondiComponentiMain();
        NavigatorView.setAuthenticatedUser(null);
        NavigatorView.navigateToHome();
    }

    private String encode(String value) {
        try {
            return java.net.URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            return value;
        }
    }

}
