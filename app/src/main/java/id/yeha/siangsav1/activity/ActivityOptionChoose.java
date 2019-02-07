package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import id.yeha.siangsav1.R;

public class ActivityOptionChoose extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_choose);

        initLayout();
        initEvent();
    }

    private void initLayout(){

        btnLogin = findViewById(R.id.btn_login_option);
        btnRegis = findViewById(R.id.btn_regis_option);

    }

    private void initEvent(){
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityOptionChoose.this,ActivityRegister.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityOptionChoose.this,LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
