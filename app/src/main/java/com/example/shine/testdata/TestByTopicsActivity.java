package com.example.shine.testdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.shine.testdata.api.CONSTANTS;
import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.models.TestListItem;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.utils.MyListItemAdapter;
import com.example.shine.testdata.utils.MyTestListItemAdapter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class TestByTopicsActivity extends Activity {

    ListView listTopics;
    public static ArrayList<TestListItem> listtestbytops = new ArrayList<TestListItem>();
    public static HttpRequest request;
    public static String qsid;
    public static String mode;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_by_topics);
        final Context context = this;

        Bundle b = getIntent().getExtras();
        qsid = b.getString("qsid");
        mode = b.getString("mode");
        Log.d("itemid", qsid);
        Log.d("mode", mode);

        listTopics = (ListView) findViewById(R.id.listTestByTopics);

        MainActivity.createParams();
        if(mode.equals("main")) {
            MainActivity.createParams();
            MainActivity.nameValuePairs.add(new BasicNameValuePair("did", CONSTANTS.DID));
            request = new HttpRequest(URLS.EVENTS_TRAIN, MainActivity.nameValuePairs);
        }
        else if (mode.equals("bytopics")) {
            MainActivity.createParams();
            MainActivity.nameValuePairs.add(new BasicNameValuePair("qsid", qsid));
            request = new HttpRequest(URLS.EVENTS_QS, MainActivity.nameValuePairs);
        }

        request.execute();

        try {
            String result = request.get().trim();
            Log.d("Result:", result);

            if(!result.equals(""))
            {
                ArrayList<String[]> list = MainActivity.parseSharpLine(result);
                Log.d("List:", list.get(0).toString());
                listtestbytops.clear();

                for(String[] item : list)  {
                    listtestbytops.add(new TestListItem(item[0], item[1], item[2]));
                }

                Log.d("List:", listtestbytops.toString());


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new MyTestListItemAdapter(listtestbytops, this);
        listTopics.setAdapter(adapter);
        listTopics.setTextFilterEnabled(true);

//        ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(this, android.R.layout.simple_list_item_1, listtops);
//        listTopics.setAdapter(adapter);


        listTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("itemClick:", "itemClick: position = " + position + ", id = "
                        + id);

                final TestListItem item = listtestbytops.get(position);
                final String itemid = item.getId();
                Log.d("itemid", itemid);

                Intent intent = new Intent(context, TestMeActivity.class);
                intent.putExtra("eid", itemid);
                startActivity(intent);

            }
        });

        listTopics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d("itemSelect", "itemSelect: position = " + position + ", id = "
                        + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("itemSelect", "itemSelect: nothing");
            }
        });

        listTopics.setOnScrollListener(new AbsListView.OnScrollListener() {
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
