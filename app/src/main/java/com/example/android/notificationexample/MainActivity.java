package com.example.android.notificationexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textviewToken);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            }
        };

        if(SharedPrefManager.getInstance(this).getToken()!=null) {
            textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            Log.d("myfcmtokenshared",SharedPrefManager.getInstance(this).getToken());
        }

        registerReceiver(broadcastReceiver,new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));
    }

    @Override
    protected void onStop()
    {
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    /*@Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        try{
            if(mHandleMessageReceiver!=null)
                unregisterReceiver(mHandleMessageReceiver);
        }catch(Exception e)
        {

        }
        super.onDestroy();

    }*/
}
