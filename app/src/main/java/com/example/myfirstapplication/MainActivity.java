package com.example.myfirstapplication;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    Button btn_welcome,btn_login;
    EditText editText1,editText2;
    TextView textView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        setContentView(R.layout.activity_main);

        btn_welcome=(Button) findViewById(R.id.main_btton1); //好像不强制转化也不会报错
        editText1=findViewById(R.id.main_input1);
        editText2=findViewById(R.id.main_input2);

        textView=findViewById(R.id.main_find);

        btn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String url0 = "https://mcmaocai.top/check";
                    URL url = new URL(url0);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(2*1000);
                    connection.setUseCaches(false);
                    connection.connect();

                    //md5加密
                    String pass=md5(editText2.getText().toString());

                    String body = "username="+editText1.getText().toString()+"&pass="+pass;
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                    writer.write(body);
                    writer.close();

                    int responseCode = connection.getResponseCode();
                    System.out.println(responseCode);
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        //得到响应流
                        System.out.println("connection");
                        System.out.println(body);
                        InputStream inputStream = connection.getInputStream();
                        //获取响应
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line;
                        while ((line = reader.readLine()) != null){
                            if(line.equals("true")){
                                Intent intent=new Intent(MainActivity.this,Welcome.class);
                                intent.putExtra("username",editText1.getText().toString());
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                            }
                        }

                        reader.close();
                        //该干的都干完了,记得把连接断了
                        connection.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }});



        btn_login=findViewById(R.id.main_btton2);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到button演示界面
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FindPass.class);
                startActivity(intent);
            }
        });
    }
    public static String md5(String string) {
        if (string.equals("")) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}