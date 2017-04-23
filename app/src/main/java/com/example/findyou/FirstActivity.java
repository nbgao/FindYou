package com.example.findyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pengbing on 2017/4/23.
 */

public class FirstActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i("FirstActivity","onCreate");
        setContentView(R.layout.first_layout);
        button = (Button)findViewById(R.id.first_btn);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("FirstActivity","onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("FirstActivity","onResume");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("FirstActivity","onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("FirstActivity","onPause");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("FirstActivity","onReStart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("FirstActivity","onDestroy");
    }

}
