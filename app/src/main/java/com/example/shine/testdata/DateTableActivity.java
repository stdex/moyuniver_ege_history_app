package com.example.shine.testdata;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.shine.testdata.models.Answer;
import com.example.shine.testdata.models.Date;
import com.example.shine.testdata.utils.MyDateListItemAdapter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DateTableActivity extends TabActivity implements TabHost.OnTabChangeListener {

    //private ListView lv1;
    TabHost myTabHost;
    String[] mDateArray;
    String[] mEventsArray;
    Multimap<String, Date> mDateTable = ArrayListMultimap.create();
    public static List<String> datestrs = new ArrayList<String>();
    public static List<Date> datelist = new ArrayList<Date>();
    public static String cent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_table);

        myTabHost = getTabHost();
        myTabHost.setOnTabChangedListener(this);

        mDateArray = getResources().getStringArray(R.array.dates);
        mEventsArray = getResources().getStringArray(R.array.events);

        for(int i = 0; i < mDateArray.length; i++)  {

            String year = mDateArray[i].substring(mDateArray[i].split("[\\{\\}]")[1].length()+2);
            String century = mDateArray[i].split("[\\{\\}]")[1];
            String event = mEventsArray[i];

            Date date = new Date(century, year, event);
            mDateTable.put(century, date);
        }

        Log.d("mDateTable: ", mDateTable.toString());


        for (String century : mDateTable.keySet()) {

            //lv1 = (ListView) findViewById(R.id.lv1);
            cent = century;

            Collection<Date> values = mDateTable.get(century);

//            datestrs.clear();
//            for (Date date : values) {
//                datestrs.add(date.getYear() + "-" + date.getEvent());
//            }

            datelist.clear();
            for (Date date : values) {
                datelist.add(date);
            }

            Intent intent = new Intent().setClass(this, TabDate.class);
            TabHost.TabSpec spec = myTabHost.newTabSpec(century).setIndicator(century).setContent(intent);

            //ourSpec.setContent(R.id.lv1);
//            ourSpec.setContent(new TabHost.TabContentFactory() {
//                public View createTabContent(String tag) {
//                    ListView lv1 = new ListView(DateTableActivity.this);
//                    String[] dateArr = new String[datestrs.size()];
//                    dateArr = datestrs.toArray(dateArr);
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DateTableActivity.this, android.R.layout.simple_list_item_1, dateArr);
//                    lv1.setAdapter(adapter);
//                    return lv1;
//                }
//            });

//            spec.setIndicator(century);
            myTabHost.addTab(spec);

//            myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
//                @Override
//                public void onTabChanged(String tabId) {
//                    myTabHost.removeView(lv1);
//                    myTabHost.addView(lv1);
//                }});


//            TabHost.TabSpec ts1 = myTabHost.newTabSpec(century);
//            ts1.setIndicator(century);
//            Log.d("century:", century);
//
//            Collection<Date> values = mDateTable.get(century);
//
//            for (Date date : values) {
//                datestrs.add(date.getYear() + "-" + date.getEvent());
//            }
//
//            ts1.setContent(new TabHost.TabContentFactory(){
//                public View createTabContent(String tag)
//                {
//                    String[] dateArr = new String[datestrs.size()];
//                    dateArr = datestrs.toArray(dateArr);
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DateTableActivity.this, android.R.layout.simple_list_item_1, dateArr);
//                    lv1.setAdapter(adapter);
//                    return lv1;
//                }
//            });
//            myTabHost.addTab(ts1);


        }

        myTabHost.getTabWidget().setCurrentTab(0);


    }

    @Override
    public void onTabChanged(String tabId) {
        ListView listDate;
        Log.i("tabs", "CurrentTab: "+myTabHost.getCurrentTab());

//        setContentView(R.layout.tab_date);

        String[] dateArr = new String[DateTableActivity.datestrs.size()];
        dateArr = DateTableActivity.datestrs.toArray(dateArr);

        listDate = (ListView) findViewById(R.id.listDate);

        ListAdapter adapter = new MyDateListItemAdapter(DateTableActivity.datelist, this);
        listDate.setAdapter(adapter);
        listDate.setTextFilterEnabled(true);

    }

}
