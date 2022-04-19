package com.littlesandbox.clicksandbox.backgroundload;

import android.app.Activity;

import com.littlesandbox.clicksandbox.R;

public class BackGroundLoad
{
    public static String[] load_all_sentens(Activity activity)
    {
        String[] all_sentens = activity.getResources().getStringArray(R.array.sentens);
        return all_sentens;
    }
    public static void load_all_sound()
    {

    }
    public static void load_all_music()
    {

    }
}
