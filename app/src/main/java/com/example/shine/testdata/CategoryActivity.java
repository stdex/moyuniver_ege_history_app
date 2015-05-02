package com.example.shine.testdata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.shine.testdata.api.CONSTANTS;
import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.net.HttpRequest;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class CategoryActivity extends Activity {

    Button button1,button2,button3,button4,button5,button6;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final Context context = this;

        Button button1 = (Button) findViewById(R.id.section_topics);
        Button button2 = (Button) findViewById(R.id.section_testme);
        Button button3 = (Button) findViewById(R.id.section_myresults);
        Button button4 = (Button) findViewById(R.id.section_datetable);
        Button button5 = (Button) findViewById(R.id.section_terms);
        Button button6 = (Button) findViewById(R.id.section_governors);


        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TopicsActivity.class);
                startActivity(intent);
            }

        });

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TestByTopicsActivity.class);
                intent.putExtra("qsid", "");
                intent.putExtra("mode", "main");
                startActivity(intent);

            }

        });

        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyResults.class);
                startActivity(intent);

            }

        });

        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DateActivity.class);
                startActivity(intent);

            }

        });

        button5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TermsActivity.class);
                startActivity(intent);

            }

        });

        button6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GovernorsActivity.class);
                startActivity(intent);

            }

        });

        return true;
    }


}
