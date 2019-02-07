package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.networks.urls.SiangsaUrl;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        initUrl();
        initSplash();


    }

    private void initSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,ActivityOptionChoose.class));
                finish();
            }
        },3000);
    }

    private void initUrl(){
        SiangsaUrl.getInstance().setApplicationContext(getApplicationContext());
        SiangsaUrl.getInstance().initiateUrl();
    }
}
