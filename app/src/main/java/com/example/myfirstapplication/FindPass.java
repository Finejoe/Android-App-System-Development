package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import com.mob.MobSDK;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FindPass extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    EditText editText1=null;
    EditText editText2=null;
    EditText editText3=null;

    TextView textView=null;
    boolean flag=false;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1)
                Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_LONG).show();
        }
    };
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            // TODO 此处为子线程！不可直接处理UI线程！处理后续操作需传到主线程中操作！
            if (result == SMSSDK.RESULT_COMPLETE) {
                //成功回调
                System.out.println("成功回调");
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    System.out.println("成功验证");
                    //短信验证码正确
                    try {
                        String url0 = "https://mcmaocai.top/modify";
                        URL url = new URL(url0);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(2*1000);
                        connection.setUseCaches(false);
                        connection.connect();

                        String pass=MainActivity.md5(editText3.getText().toString());

                        String body = "tele="+editText1.getText().toString()+"&pass="+pass;
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
//                                    Looper.prepare();
//                                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
//                                    Looper.loop();
                                    Message msg = new Message();
                                    msg.arg1 = 1;
                                    handler.sendMessage(msg);

                                    Intent intent=new Intent(FindPass.this,MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
//                                    Looper.prepare();
//                                    Toast.makeText(getApplicationContext(), "电话不存在", Toast.LENGTH_LONG).show();
//                                    Looper.loop();
                                    Message msg = new Message();
                                    msg.arg1 = 2;
                                    handler.sendMessage(msg);

                                }
                            }

                            reader.close();
                            //该干的都干完了,记得把连接断了
                            connection.disconnect();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取短信验证码成功
//                    Looper.prepare();
//                    Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_LONG).show();
//                    Looper.loop();
                }}
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);

        MobSDK.submitPolicyGrantResult(true, null);//同意协议

        SMSSDK.registerEventHandler(this.eh);//注册短信回调

        button1=findViewById(R.id.find_btton1);
        button2=findViewById(R.id.find_btton2);
        button3=findViewById(R.id.find_btton3);

        editText1=findViewById(R.id.find_input1);
        editText2=findViewById(R.id.find_input2);
        editText3=findViewById(R.id.find_input3);

        textView=findViewById(R.id.find_text1);

//        SMSSDK.getVerificationCode("86","15963302581");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("按下获取验证码:"+editText1.getText().toString());
                SMSSDK.getVerificationCode("86",editText1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag)
                SMSSDK.submitVerificationCode("86",editText1.getText().toString(),editText2.getText().toString());
                System.out.println("确认按钮已按下");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindPass.this,MainActivity.class);
                startActivity(intent);
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                flag=true;
                String username=editText3.getText().toString();
                if(!username.equals("")) {
                    if(!(6<=username.length()&&username.length()<=12)){
                        flag=false;
                        textView.setText("密码长度应在6和12之间");
                    }
                    else if(!Login.checkUsername(username)){
                        flag=false;
                        textView.setText("密码只能包含字母或数字或_");
                    }
                    else {
                        textView.setText("");
                    }
                }
                else {
                    flag=false;
                    textView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

