package com.zackeryrobinson.boundserviceswithaidl;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";
    MyBoundService myBoundService;
    private boolean isBoundConnected;
    List<String> dummyData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startService(View view) {

        String data = "somedata";


        //create an intent for the bound service
        Intent boundIntent = new Intent(this, MyBoundService.class);


        switch (view.getId()) {

            case R.id.btnBindToService:
                boundIntent.putExtra("data", "This is a bound service");
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btnInitDataForService:

                if(isBoundConnected){
                    myBoundService.initData(5);
                    Toast.makeText(myBoundService, "Data initialized", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnGetDataFromService:
                if (isBoundConnected){
                    dummyData = myBoundService.getDummyData();
                    for (int i = 0; i < dummyData.size(); i++) {
                        Toast.makeText(myBoundService, dummyData.get(i), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btnUnbindFromService:
                unbindService(serviceConnection);
                break;

        }

    }

    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        super.onStop();
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            isBoundConnected = true;
            Toast.makeText(myBoundService, "Service is bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBoundConnected = false;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    public Intent initIntent(ComponentName componentName){
        return new Intent(this, componentName.getClass());
    }


}
