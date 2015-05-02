package com.example.shine.testdata.net;

import android.util.Log;

import com.example.shine.testdata.loggers.LogCat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Request {

    public static String postRequest(String url, List<NameValuePair> nameValuePairs) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        String responseString = new String();

        try {

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity responseEntity = response.getEntity();
            if(responseEntity!=null) {
                responseString = EntityUtils.toString(responseEntity, HTTP.UTF_8);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }


    public static String getRequest(String url, List<NameValuePair> nameValuePairs) {
        HttpClient httpclient = new DefaultHttpClient();

        if(!url.endsWith("?"))
            url += "?";

        String params = URLEncodedUtils.format(nameValuePairs, "UTF-8");
        url += params;

        LogCat.v(url);
        Log.d("LOG: ", url);

        HttpGet httpget = new HttpGet(url);

        String responseString = new String();

        try {

            HttpResponse response = httpclient.execute(httpget);

            HttpEntity responseEntity = response.getEntity();
            if(responseEntity!=null) {
                responseString = EntityUtils.toString(responseEntity, HTTP.UTF_8);
            }

            Log.d("Response of GET request", responseString);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return responseString;
    }

}
