package com.zackeryrobinson.clientapp;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
