package com.example.petit.nytimessearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by petit on 7/30/2017.
 */

public class Article implements Serializable {


    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getTrumbNail() {
        return trumbNail;
    }

    String webUrl;
    String headline;
    String trumbNail;


    public Article(JSONObject jsonObject) {
        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
               if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.trumbNail = "http://www.nytimes.com/" + multimediaJson.getString("url");
               } else {
                this.trumbNail = "";
                    }
            } catch  (JSONException e){

            }
        }


    public static ArrayList<Article> fromJSONArray(JSONArray array){
        ArrayList<Article> results = new ArrayList<>();

        for (int x = 0; x< array.length(); x++){
            try{
                results.add(new Article(array.getJSONObject(x)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;

    }
}


