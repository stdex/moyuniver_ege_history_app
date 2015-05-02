package com.example.shine.testdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shine.testdata.api.CONSTANTS;
import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.models.MyTest;
import com.example.shine.testdata.models.Topic;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.utils.MyListItemAdapter;
import com.example.shine.testdata.utils.MyTestsListItemAdapter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MyResults extends Activity {

    ListView myResults;
    public static ArrayList<MyTest> listTests = new ArrayList<MyTest>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myresults);

        final Context context = this;

        myResults = (ListView) findViewById(R.id.myResults);

        MainActivity.createParams();
        HttpRequest request = new HttpRequest(URLS.MU_E, MainActivity.nameValuePairs);
        request.execute();

        try {
            String result = request.get().trim();
            Log.d("Result:", result);

            if(!result.equals(""))
            {
                ArrayList<String[]> list = MainActivity.parseSharpLine(result);
                Log.d("List:", list.get(0).toString());
                listTests.clear();

                for(String[] item : list)  {
                    listTests.add(new MyTest(item[0], item[1], item[2], item[3], item[4]));
                }

                Log.d("List:", listTests.toString());


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new MyTestsListItemAdapter(listTests, this);
        myResults.setAdapter(adapter);
        myResults.setTextFilterEnabled(true);


        myResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("itemClick:", "itemClick: position = " + position + ", id = "
                        + id);

                final MyTest item = listTests.get(position);
                final String itemid = item.getTid();
                Log.d("itemid", itemid);

                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("tid", itemid);
                startActivity(intent);

            }
        });

        myResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d("itemSelect", "itemSelect: position = " + position + ", id = "
                        + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("itemSelect", "itemSelect: nothing");
            }
        });

        myResults.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("scrollState", "scrollState = " + scrollState);
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Log.d("scroll", "scroll: firstVisibleItem = " + firstVisibleItem
                        + ", visibleItemCount" + visibleItemCount
                        + ", totalItemCount" + totalItemCount);
            }
        });

    }
}


