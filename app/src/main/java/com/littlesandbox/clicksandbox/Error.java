package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import java.io.File;

import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.LinearLayout;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import android.widget.TextView;

import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.ads.reward_video.RewardVideoAD;
import com.zh.pocket.ads.reward_video.RewardVideoADListener;
import com.zh.pocket.http.bean.ADError;

import java.util.ArrayList;

public class Error extends Activity {
    LinearLayout root;
    TextView gameDataView;
    TextView arrayView;
    ArrayList<String> s = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
        root = (LinearLayout) Select.select_id(this, R.id.root);
        gameDataView = (TextView) Select.select_id(this, R.id.gameData);
        arrayView = (TextView) Select.select_id(this, R.id.arraylist);
    }

    public void reset(View v) {
        String dir = getFilesDir().getPath();
        File f = new File(dir + "/test.txt");
        boolean result = f.delete();
        Toast.makeText(this, "删除状态" + result, Toast.LENGTH_SHORT).show();
    }

    public void enter(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    //获得文件信息
    public void get(View v) throws IOException {
        String dir = getFilesDir().getPath();
        File gameDataFile = new File(dir + "/test.txt");
        FileReader fileReader = new FileReader(gameDataFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String tmp_line;
        String result = "";
        while ((tmp_line = reader.readLine()) != null) {
            result += tmp_line;
            s.add(tmp_line);
        }
        gameDataView.setText(result);
        //  s.add(result);
        arrayView.setText(s.toString() + "arrayLsit长度" + s.size());
    }
    //测试banner
    public void testBanner(View v)
    {
        Toast.makeText(getApplicationContext(), "测试banner", Toast.LENGTH_LONG).show();
        Log.d("Error.class","测试banner");
        BannerAD banner = new BannerAD(Error.this, "53655");
        banner.setBannerADListener(new BannerADListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed(ADError adError) {
                Log.d("ADError", adError.getMessage());
                Log.d("ADError", String.valueOf(adError.getCode()));
            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADClicked() {

            }

            @Override
            public void onADClosed() {

            }
        });
        banner.loadAD((ViewGroup) findViewById(R.id.error_root));
    }
    //测试播放激励视频广告
    public void testReward(View v)
    {
        Toast.makeText(getApplicationContext(), "测试激励视频", Toast.LENGTH_LONG).show();
        //广告位已经被冻结 解冻需要2-3个小时 晚点试试
        RewardVideoAD rv = new RewardVideoAD(Error.this, "52783");
        rv.setRewardVideoADListener(new RewardVideoADListener() {
            @Override
            public void onADLoaded() {

            }

            @Override
            public void onVideoCached() {

            }

            @Override
            public void onADShow() {

            }

            @Override
            public void onADExpose() {

            }

            @Override
            public void onReward() {

            }

            @Override
            public void onADClicked() {

            }

            @Override
            public void onVideoComplete() {

            }

            @Override
            public void onADClosed() {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed(ADError adError)
            {
                Log.d("ADError", adError.getMessage());
                Log.d("ADError", String.valueOf(adError));
            }

            @Override
            public void onSkippedVideo() {

            }

            @Override
            public void onPreload() {

            }
        });
        rv.loadAD();
    }
    //测试静态插屏广告
    public void testInter(View v)
    {
        Toast.makeText(getApplicationContext(), "测试静态插屏", Toast.LENGTH_LONG).show();
        InterstitialAD inter = new InterstitialAD(Error.this, "53657");
        inter.setInterstitialADListener(new InterstitialADListener()
        {
            @Override
            public void onAdLoader() {

            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADClicked() {

            }

            @Override
            public void onADClosed() {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed(ADError adError) {
                Log.d("ADError", adError.getMessage());
                Log.d("ADError", String.valueOf(adError));
            }
        });
        //inter.show();
        inter.loadAD();
        inter.show();
    }
}
