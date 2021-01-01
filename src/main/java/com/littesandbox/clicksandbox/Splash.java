package com.littesandbox.clicksandbox;
import android.app.Activity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.animation.ObjectAnimator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnimationSet;
import android.util.Property;
import android.view.animation.AlphaAnimation;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.View;

public class Splash extends Activity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //Timer timer=new Timer();
        image =  findViewById(R.id.main);
        image.setOnClickListener(new OnClickListener()
            {

                @Override
                public void onClick(View p1) {
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);      
                }
            });

        Animation anim;
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(1000);

        image.setAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener()
            {

                @Override
                public void onAnimationStart(Animation p1) {
                }

                @Override
                public void onAnimationEnd(Animation p1) {
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }

                @Override
                public void onAnimationRepeat(Animation p1) {
                }


            });
        /*timer.schedule(new TimerTask()
         {
         @Override
         public void run() {
         }
         },1000);*/
    }


}
