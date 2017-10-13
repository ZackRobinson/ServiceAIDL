package com.zackeryrobinson.boundserviceswithaidl;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /** The primary interface we will be calling on the service. */
    IRemoteService mService = null;

    Button bind;
    Button unbind;
    Button calculate;

    private boolean mIsBound;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind = (Button)findViewById(R.id.btnBindToService);
        unbind = (Button)findViewById(R.id.btnUnbindFromService);
        calculate = (Button)findViewById(R.id.btnCalculate);




    }
}
