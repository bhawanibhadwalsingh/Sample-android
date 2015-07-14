package com.newapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReciever extends BroadcastReceiver
{

@Override
public void onReceive(Context context, Intent intent) {
    // TODO Auto-generated method stub
	if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
        Intent serviceIntent = new Intent(context, MainService.class);
        context.startService(serviceIntent);
    }
}

}