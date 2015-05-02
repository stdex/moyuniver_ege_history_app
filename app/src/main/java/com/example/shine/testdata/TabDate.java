package com.example.shine.testdata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.models.Result;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.utils.MyDateListItemAdapter;
import com.example.shine.testdata.utils.MyResultListItemAdapter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class TabDate extends Activity {

    ListView listDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_date);
//
//        String[] dateArr = new String[DateTableActivity.datestrs.size()];
//        dateArr = DateTableActivity.datestrs.toArray(dateArr);
//
//        listDate = (ListView) findViewById(R.id.listDate);
//
//        ListAdapter adapter = new MyDateListItemAdapter(DateTableActivity.datelist, this);
//        listDate.setAdapter(adapter);
//        listDate.setTextFilterEnabled(true);


    }
}
