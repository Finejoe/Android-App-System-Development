package com.example.myfirstapplication;
import Object.users;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Welcome extends AppCompatActivity {
    TextView textView1=null;
    TextView textView2=null;
    TextView textView3=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textView1=findViewById(R.id.welcome_text1);
        textView2=findViewById(R.id.welcome_text2);
        textView3=findViewById(R.id.welcome_text3);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        try {
            String url0 = "https://mcmaocai.top/find";
            URL url = new URL(url0);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(2*1000);
            connection.setUseCaches(false);
            connection.connect();

            String body = "username="+username;
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
                line = reader.readLine();
                //使用Gson解析
                Gson gson=new Gson();
                users user = gson.fromJson(line, users.class);
                System.out.println(line);
                textView1.setText("用户名:"+user.getUsername());
                textView2.setText("密码:"+user.getPass());
                textView3.setText("电话:"+user.getTele());
                reader.close();
                //该干的都干完了,记得把连接断了
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}