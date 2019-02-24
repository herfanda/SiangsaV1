package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.networks.SiangsaApi;
import id.yeha.siangsav1.networks.response.PostLoginResponse;
import id.yeha.siangsav1.util.AppConfiguration;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button   btnLogin;
    private TextView txtForgot;

    private String email, password;

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

        /*edtEmail.setText("usertest");
        edtPassword.setText("usertest");*/

    }



    private void initEvent(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillField();
                initiateLogin();
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
            AppConfiguration.getInstance().getSiangsaApi().postLogin(email,password,getLoginSuccessListener(),getErrorListener());

            //startActivity(new Intent(LoginActivity.this,MainPageActivity.class));
        }

    }

    private Response.Listener<JSONObject> getLoginSuccessListener(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                PostLoginResponse loginResponse = new PostLoginResponse(response);

                if (loginResponse.isOk()){

                    startActivity(new Intent(LoginActivity.this,MainPageActivity.class));
                    finish();
                }
            }
        };
    }

    private Response.ErrorListener getErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MESSAGE","ERROR "+error.getMessage());
                error.getMessage();
            }
        };

    }

    private void fillField(){
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
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
