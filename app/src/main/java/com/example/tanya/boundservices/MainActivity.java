package com.example.tanya.boundservices;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonBind, buttonUnbind;
    BoundService boundService;
    Boolean isBind;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.DemoBinder DemoBinder = (BoundService.DemoBinder) iBinder;
            boundService = DemoBinder.getServiceInstance();
            boundService.showmessage("Welcome to Bound Service");
            isBind = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;

        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBind = (Button) findViewById(R.id.boundservice);
        buttonUnbind = (Button) findViewById(R.id.unboundservice);
        buttonBind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BoundService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);


            }

        });
        buttonUnbind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(isBind){
                    unbindService(serviceConnection);
                    isBind=false;
                }
            }
        });
    }
}
