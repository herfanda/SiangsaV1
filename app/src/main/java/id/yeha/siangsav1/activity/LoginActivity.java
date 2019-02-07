package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.yeha.siangsav1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button   btnLogin;
    private TextView txtForgot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initLayout();
        initEvent();
    }

    private void initLayout(){
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        txtForgot = findViewById(R.id.txt_lupa);

        edtEmail.setText("usertest");
        edtPassword.setText("usertest");

    }



    private void initEvent(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateLogin();;
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Feature Disabled Currently",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initiateLogin(){

        if (isValid()){
            startActivity(new Intent(LoginActivity.this,MainPageActivity.class));
        }

    }

    /*
    * to checking username and password valid or not
    * */
    private boolean isValid(){
        boolean valid = true;

        if (edtEmail.getText().toString().equalsIgnoreCase("")){
            valid = false;
            edtEmail.setError("Harap Isi Email");
        }

        if (edtPassword.getText().toString().equalsIgnoreCase("")){
            valid = false;
            edtPassword.setError("Harap Isi Password");
        }
        return valid;
    }

    private void login(String username, EditText password){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,ActivityOptionChoose.class));
    }
}
