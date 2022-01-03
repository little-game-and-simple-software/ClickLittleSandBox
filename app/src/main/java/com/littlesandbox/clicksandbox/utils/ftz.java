package com.littlesandbox.clicksandbox.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.littlesandbox.clicksandbox.R;

public class ftz
{
    //发送通知
    public static void send_Notification(Context ctx)
    {
        int channelId=0;
        NotificationManager manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        //Notification notification = new Notification.Builder(ctx,channelId);
        Notification.Builder builder = new Notification.Builder(ctx);
        builder.setSmallIcon(R.drawable.sand);
        builder.setContentTitle("获得成就！");
        builder.setContentText("手废了没");
        Notification noti = builder.build();
        manager.notify(channelId,noti);
    }
}
