package com.example.findyou;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findyou.view.MyButton;

import static com.example.findyou.MyConstants.*;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText phonenumET;
    private MyButton incomingBtn;
    private MyButton messageBtn;
    private MyButton phoneBtn;
    private MyButton switchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonenumET = (EditText)findViewById(R.id.et);
        incomingBtn = (MyButton)findViewById(R.id.incoming);
        messageBtn = (MyButton)findViewById(R.id.message);
        phoneBtn = (MyButton)findViewById(R.id.phone);
        switchBtn = (MyButton)findViewById(R.id.myswitch);

    }

    @Override
    protected void onResume(){
        super.onResume();
        incomingBtn.setOnClickListener(this);
        messageBtn.setOnClickListener(this);
        phoneBtn.setOnClickListener(this);
        switchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.incoming:
                if(MyConstants.isIncoming){
                    MyConstants.isIncoming = false;
                    incomingBtn.setBackgroundResource(R.drawable.close_bg);
                    incomingBtn.setText("来电监控");
                }else{
                    MyConstants.isIncoming = true;
                    incomingBtn.setBackgroundResource(R.drawable.open_bg);
                    incomingBtn.setText("来电监控  已选择");
                }
                break;
            case R.id.message:
                if(MyConstants.isIncoming){
                    MyConstants.isIncoming = false;
                    messageBtn.setBackgroundResource(R.drawable.close_bg);
                    messageBtn.setText("短信监控");
                }else{
                    MyConstants.isIncoming = true;
                    messageBtn.setBackgroundResource(R.drawable.open_bg);
                    messageBtn.setText("短信监控  已选择");
                }
                break;
            case R.id.phone:
                if(MyConstants.isIncoming){
                    MyConstants.isIncoming = false;
                    phoneBtn.setBackgroundResource(R.drawable.close_bg);
                    phoneBtn.setText("去电监控");
                }else{
                    MyConstants.isIncoming = true;
                    phoneBtn.setBackgroundResource(R.drawable.open_bg);
                    phoneBtn.setText("去电监控  已选择");
                }
                break;
            case R.id.myswitch:
                MyConstants.listenerPhoneNum = phonenumET.getText().toString();         //获取电话号码
                if(MyConstants.listenerPhoneNum.equals("")){
                    Toast.makeText(MainActivity.this,"请输入监控者手机号码",Toast.LENGTH_SHORT).show();
                }else{
                    if(MyConstants.isOpen){         //开启状态 --> 关闭状态
                        MyConstants.isOpen = false;
                        incomingBtn.setEnabled(true);
                        messageBtn.setEnabled(true);
                        phoneBtn.setEnabled(true);
                        switchBtn.setText("开启监控");

                        Intent intent = new Intent(MainActivity.this,MyService.class);
                        stopService(intent);
                    }else{                          //关闭状态 --> 开启状态
                        MyConstants.isOpen = true;
                        incomingBtn.setEnabled(false);
                        messageBtn.setEnabled(false);
                        phoneBtn.setEnabled(false);
                        switchBtn.setText("关闭监控");

                        Intent intent = new Intent(MainActivity.this,MyService.class);
                        startService(intent);

                    }
                }
                break;
            default:
                break;
        }
    }

}