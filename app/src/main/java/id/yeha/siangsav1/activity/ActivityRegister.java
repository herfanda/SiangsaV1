package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.networks.urls.SiangsaUrl;
import id.yeha.siangsav1.util.AppConfiguration;
import id.yeha.siangsav1.util.Global;

public class ActivityRegister extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtNoHp;
    private EditText edtJumlahKeluarga;

    private String strUsername, strEmail, strPassword, strNoHp, strJumlahKeluarga;

    private Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        initLayout();
        initEvent();
    }

    private void initLayout(){
        edtUsername = findViewById(R.id.edt_username_regis);
        edtEmail = findViewById(R.id.edt_email_regis);
        edtPassword = findViewById(R.id.edt_password_regis);
        edtNoHp = findViewById(R.id.edt_noHp_regis);
        edtJumlahKeluarga = findViewById(R.id.edt_total_family_regis);
        btnNext = findViewById(R.id.btn_next_regis);

        strUsername = edtUsername.getText().toString();
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
        strNoHp = edtNoHp.getText().toString();
        strJumlahKeluarga = edtJumlahKeluarga.getText().toString();

    }

    private void initEvent(){

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = SiangsaUrl.getInstance().getUrl("postRegistration");

                if (isFieldEmpty()){
                    if (url != null){
                        AppConfiguration.getInstance().getSiangsaApi().postRegis(strUsername,strEmail,strPassword,strNoHp,strJumlahKeluarga,getRegisterSuccessListener(),getErrorListener());
                    } else {
                        Toast.makeText(ActivityRegister.this,"Tidak dapat melakukan pendaftaran, silahkan hubungi customer service Siangsa",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ActivityRegister.this,getResources().getString(R.string.warning_complete_regis_data),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Response.Listener<JSONObject> getRegisterSuccessListener(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(ActivityRegister.this,"Ndaftar Sukses, Monggo Login",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityRegister.this,LoginActivity.class));
                finish();
            }
        };
    }

    private Response.ErrorListener getErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityRegister.this,"Ngapunten, Ndaftar gagal",Toast.LENGTH_SHORT).show();
            }
        };

    }



    private boolean isFieldEmpty(){
        return
                edtUsername.length() > Global.DEFAULT_INT && edtEmail.length() > Global.DEFAULT_INT &&
                        edtPassword.length() > Global.DEFAULT_INT && edtNoHp.length() > Global.DEFAULT_INT &&
                        edtJumlahKeluarga.length() > Global.DEFAULT_INT;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,ActivityOptionChoose.class));
    }
}
