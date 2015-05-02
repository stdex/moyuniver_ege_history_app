package com.example.shine.testdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shine.testdata.api.CONSTANTS;
import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.models.Answer;
import com.example.shine.testdata.models.ListQuestions;
import com.example.shine.testdata.models.Question;
import com.example.shine.testdata.models.TestListItem;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.parser.ParseQ;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by SHINE on 11/30/2014.
 */
public class TestMeActivity extends Activity {
    Button nextque;
    RadioGroup rg;
    TextView que1;
    int flag=0;

    public static int marks,correct,wrong;
    public static String tid;
    public static String eid;
    public static String qid;
    public static Question question;
    public static ListQuestions questionsList;
    public static int mCurrentIndex = 0;
    public static List<String> a = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testme);

        Bundle b = getIntent().getExtras();
        eid = b.getString("eid");

        MainActivity.createParams();
        MainActivity.nameValuePairs.add(new BasicNameValuePair("eid", eid));
        HttpRequest request = new HttpRequest(URLS.RUNEVENT, MainActivity.nameValuePairs);

        request.execute();

        try {
            String result = request.get().trim();
            Log.d("Result:", result);

            questionsList = ParseQ.parseQuestions(result);
            tid = questionsList.getTid();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        String s = ""+
//                "24227#145076#2#Вопрос 1#566938#ответ 1###48862\n" +
//                "24227#145076#2##566939#ответ 2 ПР###48862\n" +
//                "24227#145076#2##566940#ответ 3###48862\n" +
//                "24227#145076#2##566941#ответ 4###48862\n" +
//                "24227#145078#2#Вопрос 3#566946#ответ 1 ПР###48858\n" +
//                "24227#145078#2##566947#ответ 2###48858\n" +
//                "24227#145078#2##566948#ответ 3###48858\n" +
//                "24227#145078#2##566949#ответ 4###48858\n" +
//                "24227#145081#2#Вопрос 6#566958#ответ 1 ПР###48861\n" +
//                "24227#145081#2##566959#ответ 2###48861\n" +
//                "24227#145081#2##566960#ответ 3###48861";

//        questionsList = ParseQ.parseQuestions(s);

        que1 = (TextView)findViewById(R.id.que1);
        nextque = (Button)findViewById(R.id.nextque1);
        rg = (RadioGroup)findViewById(R.id.radiogroup);

        updateQuestion();


        /*
        que=(TextView)findViewById(R.id.que2);
        rg=(RadioGroup)findViewById(R.id.radiogroup2);
        rb1=(RadioButton)findViewById(R.id.radioButton5);
        rb2=(RadioButton)findViewById(R.id.radioButton6);
        rb3=(RadioButton)findViewById(R.id.radioButton7);
        rb4=(RadioButton)findViewById(R.id.radioButton8);
        que.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);
        */



        nextque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                int qsize = questionsList.getmQuestions().size();
                Integer radioButtonID = rg.getCheckedRadioButtonId();
                a.add(Integer.toString(radioButtonID));
                if (qsize != 0) {

                    MainActivity.createParams();
                    MainActivity.nameValuePairs.add(new BasicNameValuePair("tid", questionsList.getTid()));
                    MainActivity.nameValuePairs.add(new BasicNameValuePair("qid", question.getQid()));
                    MainActivity.nameValuePairs.add(new BasicNameValuePair("qtype", question.getQtype()));
                    MainActivity.nameValuePairs.add(new BasicNameValuePair("tsid", question.getTsid()));
                    HttpRequest request = new HttpRequest(URLS.ASHEET, MainActivity.nameValuePairs);
                    request.execute();

                    updateQuestion();
                    Log.d("qsize:", Integer.toString(qsize));
                }
                else {
                    String[] aArr = new String[a.size()];
                    aArr = a.toArray(aArr);
                    MainActivity.createParams();
                    MainActivity.nameValuePairs.add(new BasicNameValuePair("tid", questionsList.getTid()));
                    MainActivity.nameValuePairs.add(new BasicNameValuePair("a", implode("-", aArr)));
                    HttpRequest request = new HttpRequest(URLS.FINISH, MainActivity.nameValuePairs);
                    request.execute();
                    Log.d("qsize:", Integer.toString(qsize));


                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("tid", tid);
                    startActivity(intent);
                }
                //RODO Automatically generated
                /*
                RadioButton uans=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
                String ansText=uans.getText().toString();


                if(ansText.equalsIgnoreCase(ans[flag]))
                {
                    correct++;

                }
                else
                {
                    wrong++;
                }
                flag++;
                if(flag<questions.length)
                {
                    que.setText(questions[flag]);
                    rb1.setText(options[flag*4]);
                    rb2.setText(options[(flag*4)+1]);
                    rb3.setText(options[(flag*4)+2]);
                    rb4.setText(options[(flag*4)+3]);

                }
                else {
                    marks = correct;


                    Intent in = new Intent(getApplicationContext(), BasicCResult.class);
                    startActivity(in);
                }
                */
            }
        });

    }

    public void updateQuestion() {
        rg.removeAllViews();
        Iterator<Map.Entry<String,Question>> iter = questionsList.getmQuestions().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String,Question> entry = iter.next();
            qid = entry.getKey();
            question = entry.getValue();
            que1.setText(question.getQtxt());
            //int cntans = question.getmAnswers().size();
            for(Answer ans : question.getmAnswers()) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId(Integer.valueOf(ans.getAid()));
                rdbtn.setText(ans.getAtxt());
                rg.addView(rdbtn);
            }
//            for (int i = 1; i <= cntans; i++) {
//                RadioButton rdbtn = new RadioButton(this);
//                rdbtn.setId((1 * 2) + i);
//                rdbtn.setText();
//                rg.addView(rdbtn);
//            }
            iter.remove();
            break;
        }

//        for(Map.Entry<String, Question> e : questionsList.getmQuestions().entrySet()) {
//            String qid = e.getKey();
//            Question question = e.getValue();
//            int cntans = question.getmAnswers().size();
//
//            for (int i = 1; i <= cntans; i++) {
//                RadioButton rdbtn = new RadioButton(this);
//                rdbtn.setId((1 * 2) + i);
//                rdbtn.setText("Radio " + rdbtn.getId());
//                rg.addView(rdbtn);
//            }
//
//        }


    }

    public static String implode(String glue, String[] strArray)
    {
        String ret = "";
        for(int i=0;i<strArray.length;i++)
        {
            ret += (i == strArray.length - 1) ? strArray[i] : strArray[i] + glue;
        }
        return ret;
    }

}


