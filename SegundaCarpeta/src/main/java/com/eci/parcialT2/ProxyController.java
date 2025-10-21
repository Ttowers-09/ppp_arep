package com.eci.parcialT2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import jakarta.servlet.http.HttpServletRequest;


@RestController
public class ProxyController {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL1 = "http://localhost:8082/BinariSearch";
    private static final String URL2 = "http://localhost:8081/linearsearch";

    @GetMapping("/linearsearch")
    public String linearsearch(HttpServletRequest req) throws IOException {
        String q = req.getQueryString();
        return sendGet(URL2 + "?" + q);
    }

    @GetMapping("/BinariSearch")
    public String BinariSearch(HttpServletRequest req) throws IOException {
        String q = req.getQueryString();
        return sendGet(URL1 + "?" + q);
    }

    private String sendGet (String url) throws IOException{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { 
            BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            } else {
                return "GET request not worked";
            }
        }

    }
