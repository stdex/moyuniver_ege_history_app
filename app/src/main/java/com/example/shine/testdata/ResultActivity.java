package com.example.shine.testdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.TabHost.OnTabChangeListener;

import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.models.Result;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.parser.ParseQ;
import com.example.shine.testdata.utils.MyListItemAdapter;
import com.example.shine.testdata.utils.MyResultListItemAdapter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class ResultActivity extends TabActivity implements OnTabChangeListener{

    /** Called when the activity is first created. */
    TabHost tabHost;
    public static String tid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Bundle b = getIntent().getExtras();
        tid = b.getString("tid");

        // Get TabHost Refference
        tabHost = getTabHost();

        // Set TabChangeListener called when tab changed
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;
        Intent intent;

        /************* TAB1 ************/
        // Create  Intents to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, TabTResults.class);
        spec = tabHost.newTabSpec("First").setIndicator(getResources().getString(R.string.results_tab_statistic))
                .setContent(intent);

        //Add intent to tab
        tabHost.addTab(spec);

        /************* TAB2 ************/
        intent = new Intent().setClass(this, TabResults.class);
        spec = tabHost.newTabSpec("Second").setIndicator(getResources().getString(R.string.results_tab_answers))
                .setContent(intent);
        tabHost.addTab(spec);

//        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab2);
//        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab3);

        // Set Tab1 as Default tab and change image
        tabHost.getTabWidget().setCurrentTab(0);
//        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab1_over);


    }

    @Override
    public void onTabChanged(String tabId) {

        /************ Called when tab changed *************/

        //********* Check current selected tab and change according images *******/

//        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
//        {
//            if(i==0)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab1);
//            else if(i==1)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab2);
//            else if(i==2)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab3);
//        }


        Log.i("tabs", "CurrentTab: "+tabHost.getCurrentTab());

//        if(tabHost.getCurrentTab()==0)
//            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.tab1_over);
//        else if(tabHost.getCurrentTab()==1)
//            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.tab2_over);
//        else if(tabHost.getCurrentTab()==2)
//            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.tab3_over);

    }

}

/*
public class ResultActivity extends Activity {



    TextView rv,av;
    Button rbtn;
    ListView listView ;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.result);

            rv = (TextView) findViewById(R.id.resultview);
            //  av=(TextView)findViewById(R.id.answers);
            rbtn = (Button) findViewById(R.id.restart);
            listView = (ListView) findViewById(R.id.listView);

            // Defined Array values to show in ListView
            String[] values = new String[]{"ANSWERS", "1) Native",
                    "2) Interface",
                    "3) ByteCode",
                    "4) Javac",
                    "5) Java Doc"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);
            listView.setAdapter(adapter);

            StringBuffer sb = new StringBuffer();
            sb.append("Correct Answers: " + JavaActivity.correct);
            sb.append("\nWrong Answers: " + JavaActivity.wrong);
            sb.append("\nTotal Score: " + JavaActivity.marks);
            //av.setText(JavaActivity.);
            rv.setText(sb);
            JavaActivity.correct = 0;
            JavaActivity.wrong = 0;
            rbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(in);
                }
            });


        }

}
*/