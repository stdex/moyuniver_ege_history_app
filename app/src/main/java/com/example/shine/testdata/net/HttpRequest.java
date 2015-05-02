package com.example.shine.testdata.net;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.shine.testdata.api.URLS;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.example.shine.testdata.net.Request.getRequest;

public class HttpRequest extends AsyncTask<String, Void, String> {

    public String url;
    public List<NameValuePair> nameValuePairs;
    public String httpResponse;

    public HttpRequest(String _url, ArrayList<NameValuePair> _nameValuePairs) {
        url = _url;
        nameValuePairs =_nameValuePairs;
    }

    @Override
    protected String doInBackground(String... params) {
        httpResponse = getWebPageWithData(url, nameValuePairs);
        return httpResponse;
    }

    public static String getWebPageWithData( String url, List<NameValuePair> nameValuePairs ){
        String response = "";
        response = getRequest(url, nameValuePairs);
        return response;
    }

    @Override
    public String toString() {
        return String.format("URL: " + url + "\n" + "nameValuePairs: " + nameValuePairs.toString() + "\n" + "httpResponse: " + httpResponse);
    }

}
