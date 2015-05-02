package com.example.shine.testdata;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.models.Result;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.utils.MyResultListItemAdapter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class TabResults extends Activity {

    ListView listResults;

    public static ArrayList<Result> listres = new ArrayList<Result>();
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_results);

        MainActivity.createParams();
        MainActivity.nameValuePairs.add(new BasicNameValuePair("tid", ResultActivity.tid));
        HttpRequest request = new HttpRequest(URLS.RESULTS, MainActivity.nameValuePairs);

        request.execute();

        try {
            String result = request.get().trim();
            Log.d("Result:", result);

            if(!result.equals(""))
            {
                listResults = (ListView) findViewById(R.id.listResults);
                ArrayList<String[]> list = MainActivity.parseSharpLine(result);

                Log.d("List:", Arrays.toString(list.get(0)));

                listres.clear();

                for(String[] item : list)  {
                    listres.add(new Result(item[0], item[1], item[2], item[3], item[4]));
                }

                Log.d("List:", listres.toString());

                ListAdapter adapter = new MyResultListItemAdapter(listres, this);
                listResults.setAdapter(adapter);
                listResults.setTextFilterEnabled(true);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
