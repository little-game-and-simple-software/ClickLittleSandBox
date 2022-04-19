package com.littlesandbox.clicksandbox.Listeners;

import android.media.SoundPool;
import android.util.Log;

import com.littlesandbox.clicksandbox.EasySoundPool;

public class EasySoundPoolListener implements SoundPool.OnLoadCompleteListener
{
    private String tag;
    public EasySoundPoolListener()
    {
        tag = this.getClass().toString();
    }
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
    {
        if(sampleId == EasySoundPool.click_stream_id)
        {
            Log.d(tag,"正在加载的音频id" + sampleId);
            Log.d(tag, "音频加载状态" + status);
            if(status == 0)
            {
                Log.d(tag,"音频" + sampleId + "加载成功");
                EasySoundPool.click_sound_load_status = true;
            }
        }
    }
}
