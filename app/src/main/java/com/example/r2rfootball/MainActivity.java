package com.example.r2rfootball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r2rfootball.ui.matchOverview.Match;

public class MainActivity extends AppCompatActivity {

    Animation app_slash,beta2;
    ImageView logosplash;
    TextView textsplash;
    Button mulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        app_slash = AnimationUtils.loadAnimation(this,R.anim.app_splash);
        beta2 = AnimationUtils.loadAnimation(this, R.anim.beta2);

        logosplash = findViewById(R.id.logo_app);
//        mulai = findViewById(R.id.mulai);
        logosplash.startAnimation(app_slash);
//        setting timer untuk 2 detik
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //merubah ke activity lain
                Intent gogetstarted = new Intent(MainActivity.this, Match.class);
                startActivity(gogetstarted);
                finish();

            }
        },2000); //2000ms = 2 detik
    }


}
