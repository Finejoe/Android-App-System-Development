package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {
    boolean checkFlag1=false,checkFlag2=false,checkFlag3=false,checkFlag4=false;
    EditText editText1=null;
    EditText editText2=null;
    EditText editText3=null;
    EditText editText4=null;
    TextView textView=null;
    ImageView imageView1=null;
    ImageView imageView2=null;
    ImageView imageView3=null;
    ImageView imageView4=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView=findViewById(R.id.login_text1);

        editText1=findViewById(R.id.login_input1);
        editText2=findViewById(R.id.login_input2);
        editText3=findViewById(R.id.login_input3);
        editText4=findViewById(R.id.login_input4);

        imageView1=findViewById(R.id.check_1);
        imageView2=findViewById(R.id.check_2);
        imageView3=findViewById(R.id.check_3);
        imageView4=findViewById(R.id.check_4);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkFlag1=false;
                    boolean flag=true;
                    String username=editText1.getText().toString();
                    if(!username.equals("")) {
                        if(!(65<=username.charAt(0)&&username.charAt(0)<=90||97<=username.charAt(0)&&username.charAt(0)<=122)){
                            flag=false;
                            textView.setText("账号需字母开头");
                        }
                        else if(!(5<=username.length()&&username.length()<=10)){
                            flag=false;
                            textView.setText("账号长度应在5和10之间");
                        }
                        else if(!checkUsername(username)){
                            flag=false;
                            textView.setText("账号只能包含字母或数字或_");
                        }
                        else if(!checkCaps(username)){
                            flag=false;
                            textView.setText("账号至少包含一个大写字母");
                        }
                        else {
                            textView.setText("");
                            checkFlag1=true;
                        }
                        if (flag) {
                            imageView1.setBackgroundResource(R.drawable.correct);
                            imageView1.setVisibility(View.VISIBLE);
                        } else {
                            imageView1.setBackgroundResource(R.drawable.wrong);
                            imageView1.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        imageView1.setVisibility(View.INVISIBLE);
                        textView.setText("");
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFlag2=false;
                boolean flag=true;
                String username=editText2.getText().toString();
                if(!username.equals("")) {
                        if(!(6<=username.length()&&username.length()<=12)){
                        flag=false;
                        textView.setText("密码长度应在6和12之间");
                    }
                    else if(!checkUsername(username)){
                        flag=false;
                        textView.setText("密码只能包含字母或数字或_");
                    }
                    else {
                        textView.setText("");
                        checkFlag2=true;
                        }
                    if (flag) {
                        imageView2.setBackgroundResource(R.drawable.correct);
                        imageView2.setVisibility(View.VISIBLE);
                    } else {
                        imageView2.setBackgroundResource(R.drawable.wrong);
                        imageView2.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    imageView2.setVisibility(View.INVISIBLE);
                    textView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFlag3=false;
                boolean flag=true;
                String username=editText3.getText().toString();
                if(!username.equals("")) {
                    if(!(username.equals(editText2.getText().toString()))){
                        flag=false;
                        textView.setText("两次的密码应该相同");
                    }
                    else {
                        textView.setText("");
                        checkFlag3=true;
                    }
                    if (flag) {
                        imageView3.setBackgroundResource(R.drawable.correct);
                        imageView3.setVisibility(View.VISIBLE);
                    } else {
                        imageView3.setBackgroundResource(R.drawable.wrong);
                        imageView3.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    imageView3.setVisibility(View.INVISIBLE);
                    textView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFlag4=false;
                boolean flag=true;
                String username=editText4.getText().toString();
                if(username.equals("")) {
                        flag=false;
                        textView.setText("手机号不能为空");
                    }
                else {
                    textView.setText("");
                    checkFlag4=true;
                }
                if (flag) {
                    imageView4.setBackgroundResource(R.drawable.correct);
                    imageView4.setVisibility(View.VISIBLE);
                } else {
                    imageView4.setBackgroundResource(R.drawable.wrong);
                    imageView4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Button btn1=findViewById(R.id.login_button1);
        Button btn2=findViewById(R.id.login_button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(checkFlag1&&checkFlag2&&checkFlag3&&checkFlag4)){
                    Toast.makeText(Login.this,"信息不完整，无法注册",Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        String url0 = "https://mcmaocai.top/disposal";
                        URL url = new URL(url0);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(2 * 1000);
                        connection.setUseCaches(false);
                        connection.connect();

                        String pass=MainActivity.md5(editText2.getText().toString());

                        String body = "username=" + editText1.getText().toString()
                                + "&pass=" + pass
                                + "&tele=" + editText4.getText().toString();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                        writer.write(body);
                        writer.close();

                        int responseCode = connection.getResponseCode();
                        System.out.println(responseCode);
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            //得到响应流
                            System.out.println("connection");
                            System.out.println(body);
                            InputStream inputStream = connection.getInputStream();
                            //获取响应
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                Toast.makeText(Login.this, line, Toast.LENGTH_SHORT).show();
                                if(line.equals("注册成功")){
                                    Intent intent=new Intent(Login.this,Welcome.class);
                                    intent.putExtra("username",editText1.getText().toString());
                                    startActivity(intent);
                                }
                            }
                            reader.close();
                            //该干的都干完了,记得把连接断了
                            connection.disconnect();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }});

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public static boolean checkUsername(String username){
        boolean flag=true;
        for (int i = 0; i < username.length(); i++) {
            int ascii = username.charAt(i);
            if (48 <= ascii && ascii <= 57 || 65 <= ascii && ascii <= 90 || 97 <= ascii && ascii <= 122 || ascii == 95)
                ;
            else flag = false;
        }
        return flag;
    }
    public static boolean checkCaps(String username){
        boolean flag=false;
        for (int i = 0; i < username.length(); i++) {
            int ascii = username.charAt(i);
            if (65 <= ascii && ascii <= 90)
                flag=true;
        }
        return flag;
    }
}