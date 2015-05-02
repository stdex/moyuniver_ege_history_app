package com.example.shine.testdata;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.shine.testdata.api.URLS;
import com.example.shine.testdata.loggers.LogCat;
import com.example.shine.testdata.net.HttpRequest;
import com.example.shine.testdata.parser.CSVReader;
import com.example.shine.testdata.store.ObscuredSharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.shine.testdata.net.Request.getRequest;
import static com.example.shine.testdata.net.Request.postRequest;


public class RegistrationActivity extends Activity {

    EditText editTextUserName, editTextPassword;
    Button btnBack;
    Button btnRegistration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        final EditText editTextUserName=(EditText)findViewById(R.id.user);
        final EditText editTextPassword=(EditText)findViewById(R.id.password);

        final Context context = this;
        Button btnBack=(Button)findViewById(R.id.back);
        Button btnRegistration=(Button)findViewById(R.id.registration);


        btnRegistration.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                MainActivity.login = editTextUserName.getText().toString();
                MainActivity.pass = editTextPassword.getText().toString();

                MainActivity.createParams();
                MainActivity.nameValuePairs.add(new BasicNameValuePair("email", MainActivity.login));
                MainActivity.nameValuePairs.add(new BasicNameValuePair("pass", MainActivity.pass));
                HttpRequest request = new HttpRequest(URLS.REGISTRATION, MainActivity.nameValuePairs);
                request.execute();

                try {
                    String result = request.get().trim();
                    Log.d("Result:", result);

                    if(!result.equals("0") && !result.equals("") && !MainActivity.login.equals("") && !MainActivity.pass.equals(""))
                    {
                        ArrayList<String[]> list = MainActivity.parseSharpLine(result);

                        Log.d("List:", Arrays.toString(list.get(0)));
                        MainActivity.memberid = list.get(0)[0];

                        Intent intent = new Intent(context, CategoryActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.error_login_pass_reg), Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

//                createParams();
//                GetDataTask task = new GetDataTask();
//                task.execute(nameValuePairs);
//
//                String phoneid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//                String login = editTextUserName.getText().toString();
//                String pass = editTextPassword.getText().toString();
//                String loginInfo = "";
//
//                //String storedPassword=LoginActivity.getSinlgeEntry(userName);
//
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                nameValuePairs.add(new BasicNameValuePair("email", login));
//                nameValuePairs.add(new BasicNameValuePair("pass", pass));
//                nameValuePairs.add(new BasicNameValuePair("os", "android"));
//                nameValuePairs.add(new BasicNameValuePair("phoneid", phoneid));
//
//                loginInfo = getRequest(URLS.REGISTRATION, nameValuePairs);
//
//                if(!login.isEmpty() &&  !pass.isEmpty() && loginInfo != "0" && !loginInfo.isEmpty())
//                {
//                    Reader reader = new StringReader(loginInfo);
//
//                    String next[] = {};
//                    List<String[]> list = new ArrayList<String[]>();
//
//                    try {
//                        CSVReader csvObj = new CSVReader(reader, '#');
//                        while(true) {
//                            next = csvObj.readNext();
//                            if(next != null) {
//                                list.add(next);
//                            } else {
//                                break;
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    LogCat.v(list.get(0).toString());
//
//                    Intent intent = new Intent(context, CategoryActivity.class);
//                    startActivity(intent);
//
//                }
//                else
//                {
//                    Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.error_login_pass_reg), Toast.LENGTH_LONG).show();
//                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //LoginActivity=new LoginActivity(this);
    //LoginActivity=LoginActivity.open();




//    SharedPreferences userDetails = context.getSharedPreferences("userdetails", MODE_PRIVATE);
//    Editor edit = userDetails.edit();
//    edit.clear();
//    edit.putString("username", txtUname.getText().toString().trim());
//    edit.putString("password", txtPass.getText().toString().trim());
//    edit.commit();
//    Toast.makeText(context, "Login details are saved..", 3000).show();
//
//    SharedPreferences userDetails = context.getSharedPreferences("userdetails", MODE_PRIVATE);
//    String Uname = userDetails.getString("username", "");
//    String pass = userDetails.getString("password", "");
//
//    Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
//    final SharedPreferences prefs = new ObscuredSharedPreferences(this, this.getSharedPreferences(MY_PREFS_FILE_NAME, Context.MODE_PRIVATE) );
//
//    prefs.edit().putString("foo","bar").commit();
//    prefs.getString("foo", null);


}
