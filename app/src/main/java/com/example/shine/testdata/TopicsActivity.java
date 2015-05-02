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
import com.example.shine.testdata.models.Topic;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.utils.MyListItemAdapter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class TopicsActivity extends Activity {

    ListView listTopics;
    public static ArrayList<Topic> listtops = new ArrayList<Topic>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics);

        final Context context = this;

        listTopics = (ListView) findViewById(R.id.listTopics);

        MainActivity.createParams();
        MainActivity.nameValuePairs.add(new BasicNameValuePair("did", CONSTANTS.DID));
        HttpRequest request = new HttpRequest(URLS.EVENTS_TOPICS, MainActivity.nameValuePairs);
        request.execute();

        try {
            String result = request.get().trim();
            Log.d("Result:", result);

            if(!result.equals(""))
            {
                ArrayList<String[]> list = MainActivity.parseSharpLine(result);
                Log.d("List:", list.get(0).toString());
                listtops.clear();

                for(String[] item : list)  {
                    listtops.add(new Topic(item[0], item[1]));
                }

                Log.d("List:", listtops.toString());


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new MyListItemAdapter(listtops, this);
        listTopics.setAdapter(adapter);
        listTopics.setTextFilterEnabled(true);

//        ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(this, android.R.layout.simple_list_item_1, listtops);
//        listTopics.setAdapter(adapter);


        listTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("itemClick:", "itemClick: position = " + position + ", id = "
                        + id);

                final Topic item = listtops.get(position);
                final String itemid = item.getId();
                Log.d("itemid", itemid);

                Intent intent = new Intent(context, TestByTopicsActivity.class);
                intent.putExtra("qsid", itemid);
                intent.putExtra("mode", "bytopics");
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
