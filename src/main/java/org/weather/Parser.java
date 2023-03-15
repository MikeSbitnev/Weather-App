package org.weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
 class DictionaryGETRequestExample {

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {}.getType()
                );
        return map;
    }

    public static void main(String[] args) {
        String API_KEY = "9ed2234a8d17836ddcd1b0783c1c37be";
        String LOCATION = "Kyiv,UA";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?lat=50.45&lon=30.52&appid=9ed2234a8d17836ddcd1b0783c1c37be&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result);

            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
            Map<String, Object> cloudsMap = jsonToMap(respMap.get("clouds").toString());
            Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());


            System.out.println("Current Temperature: " + mainMap.get("temp"));
            System.out.println("Current Humidity: " + mainMap.get("humidity"));
            System.out.println("Pressure: " + mainMap.get("pressure"));
            System.out.println("Cloudiness, %: " + cloudsMap.get("all"));
            System.out.println("Wind Speeds: " + windMap.get("speed"));
            System.out.println("Wind Angle: " + windMap.get("deg"));
            System.out.println("Sunrise time " + sysMap.get("sunrise"));
            System.out.println("Sunset time " + sysMap.get("sunset"));


            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
