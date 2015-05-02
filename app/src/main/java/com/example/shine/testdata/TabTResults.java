package com.example.shine.testdata;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.net.HttpRequest;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class TabTResults extends Activity {

    public static String date;
    public static String time;
    public static String qtotal;
    public static String nanswered;
    public static String ncorrect;
    public static String ncheated;
    public static String mark;

    TextView tab_tresults;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_tresults);

        MainActivity.createParams();
        MainActivity.nameValuePairs.add(new BasicNameValuePair("tid", ResultActivity.tid));
        HttpRequest request = new HttpRequest(URLS.TRESULTS, MainActivity.nameValuePairs);

        request.execute();

        try {
            String result = request.get().trim();
            Log.d("Result:", result);

            if(!result.equals(""))
            {
                ArrayList<String[]> list = MainActivity.parseSharpLine(result);

                Log.d("List:", Arrays.toString(list.get(0)));

                date = list.get(0)[0];
                time = list.get(0)[1];
                qtotal = list.get(0)[2];
                nanswered = list.get(0)[3];
                ncorrect = list.get(0)[4];
                ncheated = list.get(0)[5];
                mark = list.get(0)[6];

                String tab_statistic = "Дата: " + date + "\n" + "Время: " + time + "\n" + "Всего вопросов: " + qtotal + "\n" + "Ответов: " + nanswered + "\n" + "Правильных: " + ncorrect + "\n" + "Неправильных: " + Integer.toString(Integer.parseInt(qtotal) - Integer.parseInt(ncorrect)) + "\n" + "Оценка: " + mark;

                tab_tresults = (TextView)findViewById(R.id.tab_tresults);
                tab_tresults.setText(tab_statistic);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}