package com.littlesandbox.clicksandbox;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.Animation;
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
        image =  findViewById(R.id.main);
        image.setOnClickListener(new OnClickListener()
            {

                @Override
                public void onClick(View p1) {
                    Intent i=new Intent(getApplicationContext(), Error.class);
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
                    Intent i=new Intent(getApplicationContext(), Error.class);
                     startActivity(i);
                }

                @Override
                public void onAnimationRepeat(Animation p1) {
                }


            });
    
    }


}
