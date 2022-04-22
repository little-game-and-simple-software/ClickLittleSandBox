package com.littlesandbox.clicksandbox;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.littlesandbox.clicksandbox.AdListener.BannerListener;
import com.zh.pocket.ads.banner.BannerAD;

public class Achievement extends Activity
{
    //立即获得成就按钮
    protected View get_achievement;
    protected LinearLayout achive_banner_layout;
    protected TextView tv_achive_state1;

    public BannerAD bannerAD;
    public BannerListener bannerListener;
    public static Context ctx;
    //界面
    public static class Views
    {
        public static LinearLayout achive_roots;
        public static View achive_release_hand_view;
        public static ImageView achive_img1;
        public static Button achive_now1;

        public static View achive_auto_view;
        public static TextView achive_title2;
        public static TextView achive_how_to_get2;
        public static TextView achive_progress2;
        public static Button achive_now2;
        public static ImageView achive_img2;

        public static View achive_hand_view;
        public static TextView achive_title3;
        public static TextView achive_how_to_get3;
        public static TextView achive_progress3;
        public static Button achive_now3;
        public static ImageView achive_img3;

        public static View achive_da_zhuang;
        public static TextView achive_title4;
        public static TextView achive_how_to_get4;
        public static TextView achive_progress4;
        public static Button achive_now4;
        public static ImageView achive_img4;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        ctx= getApplicationContext();
        achive_banner_layout = findViewById(R.id.achive_banner);
        bannerAD = new BannerAD(this,Global.bannerid);
        bannerListener = new BannerListener(Achievement.this);
        bannerAD.setBannerADListener(bannerListener);

        bannerAD.loadAD(achive_banner_layout);
        //先找到界面 再修改数据
        init_view();
        initAchiveLayoutData();
        RadioGroup achive_filter_group = findViewById(R.id.achive_filter);
        achive_filter_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                Toast.makeText(Achievement.this,"功能未完成",Toast.LENGTH_LONG).show();
                /*RadioButton clicked = (RadioButton) radioGroup.getChildAt(i);
                clicked.setTextColor(Color.YELLOW);
                clicked.setBackgroundColor(Color.rgb(139,195,74));*/
            }
        });
        load_game_data();
    }
    public void change_view_mode(View v)
    {
        Toast.makeText(Achievement.this,"功能未完成",Toast.LENGTH_LONG).show();
    }
    //接在存档
    private void load_game_data()
    {
        AchivementStruct achivement_data1 = Game.load_achivement(ctx, 0);
        AchivementStruct achivement_data2 = Game.load_achivement(ctx,1);
        AchivementStruct achivement_data3 = Game.load_achivement(ctx,2);
        AchivementStruct achivement_data4 = Game.load_achivement(ctx,3);

        AchivementStruct[] achivementStructs = new AchivementStruct[4];
        achivementStructs[0] = achivement_data1;
        achivementStructs[1] = achivement_data2;
        achivementStructs[2] = achivement_data3;
        achivementStructs[3] = achivement_data4;

        setAchiveLayoutData(achivementStructs);
    }
    private void setAchiveLayoutData(AchivementStruct[] achivementStructs)
    {
        for(int i = 0 ;i < achivementStructs.length; i++)
        {
            //下次再做！ 2022-4-21

        }
    }
    private void init_view()
    {
        //Achievement.views
        Views.achive_roots = findViewById(R.id.achives_root);
        //1
        Views.achive_release_hand_view = Views.achive_roots.findViewById(R.id.include_1);
        Views.achive_img1 = Views.achive_release_hand_view.findViewById(R.id.achive_img);
        Views.achive_now1 = Views.achive_release_hand_view.findViewById(R.id.achive_now);
        //2
        Views.achive_auto_view = Views.achive_roots.findViewById(R.id.include_2);
        Views.achive_img2 = Views.achive_auto_view.findViewById(R.id.achive_img);
        Views.achive_title2 = Views.achive_auto_view.findViewById(R.id.achive_title);
        Views.achive_how_to_get2 = Views.achive_auto_view.findViewById(R.id.achive_how_to_get);
        Views.achive_progress2 = Views.achive_auto_view.findViewById(R.id.achive_progess);
        Views.achive_now2 = Views.achive_auto_view.findViewById(R.id.achive_now);
        //3
        Views.achive_hand_view = Views.achive_roots.findViewById(R.id.include_3);
        Views.achive_img3 = Views.achive_hand_view.findViewById(R.id.achive_img);
        Views.achive_title3 = Views.achive_hand_view.findViewById(R.id.achive_title);
        Views.achive_how_to_get3 = Views.achive_hand_view.findViewById(R.id.achive_how_to_get);
        Views.achive_progress3 = Views.achive_hand_view.findViewById(R.id.achive_progess);
        Views.achive_now3 = Views.achive_hand_view.findViewById(R.id.achive_now);
        //4
        Views.achive_da_zhuang = Views.achive_roots.findViewById(R.id.include_4);
        Views.achive_img4 = Views.achive_da_zhuang.findViewById(R.id.achive_img);
        Views.achive_title4 = Views.achive_da_zhuang.findViewById(R.id.achive_title);
        Views.achive_how_to_get4 = Views.achive_da_zhuang.findViewById(R.id.achive_how_to_get);
        Views.achive_progress4 = Views.achive_da_zhuang.findViewById(R.id.achive_progess);
        Views.achive_now4 = Views.achive_da_zhuang.findViewById(R.id.achive_now);
    }
    //备份方法
    private void initAchiveLayoutData()
    {
        Views.achive_img1.setImageResource(R.drawable.unknown);
        Views.achive_img2.setImageResource(R.drawable.unknown);
        Views.achive_img3.setImageResource(R.drawable.unknown);
        Views.achive_img4.setImageResource(R.drawable.unknown);

        Views.achive_title2.setText("手废了没");
        Views.achive_how_to_get2.setText("点击次数超300次获得");
        Views.achive_progress2.setText("300");

        Views.achive_title3.setText("人型打桩机");
        Views.achive_how_to_get3.setText("点击次数超过1000次获得");
        Views.achive_how_to_get3.setText("1000");
        Views.achive_img3.setImageResource(R.drawable.unknown);

        Views.achive_title4.setText("持之以恒");
        Views.achive_how_to_get4.setText("获得所有句子后获得");
        Views.achive_img4.setImageResource(R.drawable.unknown);

        Views.achive_now1.setOnClickListener(new AchiveNowListener(0,Views.achive_img1));
        Views.achive_now2.setOnClickListener(new AchiveNowListener(1,Views.achive_img2));
        Views.achive_now3.setOnClickListener(new AchiveNowListener(2,Views.achive_img3));
        Views.achive_now4.setOnClickListener(new AchiveNowListener(3,Views.achive_img4));
    }
    class AchiveNowListener implements View.OnClickListener
    {
        int button_id;
        ImageView img;
        public AchivementStruct achivementData;
        public AchiveNowListener(){}
        public AchiveNowListener(int id, ImageView p_img)
        {
            this.button_id = id;
            this.img = p_img;
            achivementData = new AchivementStruct();
        }
        @Override
        public void onClick(View view)
        {
            //这里就不较真了 反正调用广告次数过多出现问题
            if(button_id == 0)
            {
               // Log.d("img",img.toString());
                img.setImageResource(R.drawable.auto);
                achivementData.lock_status = false;
                achivementData.title = "自动点击-释放双手";
                achivementData.progress = 1;
                achivementData.current_progress = 1;
                Game.save_achivement(ctx, achivementData,0);
            }
            if(button_id == 1)
            {
                img.setImageResource(R.drawable.hand);
                achivementData.lock_status = false;
                achivementData.title = "手废了没";
                achivementData.progress = 300;
                achivementData.current_progress = 300;
                Game.save_achivement(ctx, achivementData,1);
            }
            if(button_id == 2)
            {
                img.setImageResource(R.drawable.dzj);
                achivementData.lock_status = false;
                achivementData.title = "人型打桩机";
                achivementData.progress = 1000;
                achivementData.current_progress = 1000;
                Game.save_achivement(ctx, achivementData, 2);
            }
            if(button_id == 3)
            {
                img.setImageResource(R.drawable.czyh);
                achivementData.lock_status = false;
                achivementData.title = "持之以恒";
                achivementData.progress = 1;
                achivementData.current_progress = 1;
                Game.save_achivement(ctx, achivementData, 3);
            }
            bannerAD.loadAD(achive_banner_layout);
        }
    }
}