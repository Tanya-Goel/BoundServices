package com.example.tanya.boundservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BoundService extends Service {
IBinder ibinder=new DemoBinder();
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service Bind", Toast.LENGTH_SHORT).show();
        return ibinder;
    }
    class DemoBinder extends Binder
    {
        public BoundService getServiceInstance(){
            return BoundService.this;
        }

    }
    public void showmessage(String message){
        Toast.makeText(BoundService.this, "Message: "+ message ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Service unbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}

