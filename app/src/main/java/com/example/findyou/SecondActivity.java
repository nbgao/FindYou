package com.example.findyou;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Pengbing on 2017/4/23.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Log.i("SecondActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SecondActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SecondActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SecondActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SecondActivity","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("SecondActivity","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SecondActivity","onDestroy");
    }
}
