package com.example.shine.testdata;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import com.example.shine.testdata.api.CONSTANTS;
import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.loggers.LogCat;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.parser.CSVReader;


import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.example.shine.testdata.net.Request.getRequest;
import static com.example.shine.testdata.net.Request.postRequest;

public class MainActivity extends Activity {
    EditText editTextUserName,editTextPassword;
    Button btnSignIn;
    Button btnSignReg;
    //LoginActivity LoginActivity;

    public static String login = "";
    public static String pass = "";
    public static String memberid = "";
    public static String paidtill = "";
    public static String uid = "";
    public static String phoneid = "";
    public static String width = "";
    public static String height = "";

    public static ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        phoneid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        width = Integer.toString(getWindowManager().getDefaultDisplay().getWidth());
        height = Integer.toString(getWindowManager().getDefaultDisplay().getHeight());

        //LoginActivity=new LoginActivity(this);
        //LoginActivity=LoginActivity.open();

        final EditText editTextUserName=(EditText)findViewById(R.id.user);
        final EditText editTextPassword=(EditText)findViewById(R.id.password);

        final Context context = this;
        Button btnSignIn=(Button)findViewById(R.id.in);
        Button btnSignReg=(Button)findViewById(R.id.reg);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                login = editTextUserName.getText().toString();
                pass = editTextPassword.getText().toString();

                createParams();
                HttpRequest request = new HttpRequest(URLS.LOGIN, nameValuePairs);
                request.execute();

//                GetDataTask task = new GetDataTask();
//                task.execute(nameValuePairs);

//                FutureTask<String> task = new FutureTask(new Callable<String>() {
//                    public String call() {
//                        String loginInfo = "";
//                        loginInfo = getRequest(URLS.LOGIN, nameValuePairs);
//                        return loginInfo;
//                    }
//                });
//                new Thread(task).start();

                try {
                    String result = request.get().trim();
                    Log.d("Result:", result);

                    if(!result.equals("0") && !result.equals("") && !login.equals("") && !pass.equals(""))
                    {
                        ArrayList<String[]> list = parseSharpLine(result);

                        Log.d("List:", Arrays.toString(list.get(0)));
                        memberid = list.get(0)[0];
                        paidtill = list.get(0)[2];
                        uid = list.get(0)[3];

                        Intent intent = new Intent(context, CategoryActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.error_login_pass), Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });

        btnSignReg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(context, RegistrationActivity.class);
                startActivity(intent);

            }
        });


    }


    private class GetDataTask extends AsyncTask<ArrayList<NameValuePair>, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Calculating...");
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.show();
        }

        @Override
        protected String doInBackground(ArrayList<NameValuePair>... nameValuePairs) {
            String response = "";
            response = getRequest(URLS.LOGIN, nameValuePairs[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            dialog.dismiss();
        }
    }


    public static void createParams()
    {
        nameValuePairs.clear();

        nameValuePairs.add(new BasicNameValuePair("login", login));
        nameValuePairs.add(new BasicNameValuePair("pass", pass));
        nameValuePairs.add(new BasicNameValuePair("memberid", memberid));
        nameValuePairs.add(new BasicNameValuePair("phoneid", phoneid));
        nameValuePairs.add(new BasicNameValuePair("appid", CONSTANTS.APPID));
        nameValuePairs.add(new BasicNameValuePair("appsgn", CONSTANTS.APPSGN));
        nameValuePairs.add(new BasicNameValuePair("appcode", phoneid));
        nameValuePairs.add(new BasicNameValuePair("os", CONSTANTS.OS));
        nameValuePairs.add(new BasicNameValuePair("ver", CONSTANTS.VER));
        nameValuePairs.add(new BasicNameValuePair("width", width));
        nameValuePairs.add(new BasicNameValuePair("height", height));

    }


    public static ArrayList<String[]> parseSharpLine(String result)
    {
        Reader reader = new StringReader(result);

        String next[] = {};
        ArrayList<String[]> requestlist = new ArrayList<String[]>();

        try {
            CSVReader csvObj = new CSVReader(reader, '#');
            while(true) {
                next = csvObj.readNext();
                if(next != null) {
                    requestlist.add(next);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requestlist;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}