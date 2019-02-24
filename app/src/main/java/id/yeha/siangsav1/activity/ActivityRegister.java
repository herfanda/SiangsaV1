package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.networks.VolleySingleton;
import id.yeha.siangsav1.networks.request.VolleyJsonObjectRequest;
import id.yeha.siangsav1.networks.response.PostRegisResponse;
import id.yeha.siangsav1.networks.urls.SiangsaUrl;
import id.yeha.siangsav1.util.AppConfiguration;
import id.yeha.siangsav1.util.Global;

public class ActivityRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edtUsername;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtNoHp;
    private EditText edtJumlahKeluarga;
    private Spinner spLayanan;
    private EditText edtAddress;

    private String strUsername, strEmail, strPassword, strNoHp, strJumlahKeluarga, lat,lng,address;
    private Integer layananId;

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
        spLayanan = findViewById(R.id.sp_layanan);
        edtAddress = findViewById(R.id.edt_address);
        btnNext = findViewById(R.id.btn_next_regis);
    }

    private void initEvent(){

        // Spinner click listener
        spLayanan.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Pilih Layanan");
        categories.add("Rumah Tangga");
        categories.add("Niaga");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spLayanan.setAdapter(dataAdapter);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = SiangsaUrl.getInstance().getUrl("postRegistration");
                fillField();
                if (isFieldEmpty()){
                    if (url != null){
                        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,null,getRegisterSuccessListener(),getErrorListener());
                        VolleySingleton.getInstance().addToRequestQueue(request);*/
                        AppConfiguration.getInstance().getSiangsaApi().postRegis(strUsername,strEmail,strPassword,strNoHp,address,"","",layananId,"","",getRegisterSuccessListener(),getErrorListener());
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
                PostRegisResponse regisResponse = new PostRegisResponse(response);

                if (regisResponse.isOk()){
                    Toast.makeText(ActivityRegister.this,"Anda Berhasil Registrasi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityRegister.this,LoginActivity.class));
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



    private boolean isFieldEmpty(){
        return
                edtUsername.length() > Global.DEFAULT_INT && edtEmail.length() > Global.DEFAULT_INT &&
                        edtPassword.length() > Global.DEFAULT_INT && edtNoHp.length() > Global.DEFAULT_INT &&
                        edtJumlahKeluarga.length() > Global.DEFAULT_INT && layananId != Global.DEFAULT_INT;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,ActivityOptionChoose.class));
    }

    private void fillField(){
        strUsername = edtUsername.getText().toString();
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
        strNoHp = edtNoHp.getText().toString();
        strJumlahKeluarga = edtJumlahKeluarga.getText().toString();
        address = edtAddress.getText().toString();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (position == 1){
            layananId = 1;
        } else if (position == 2){
            layananId = 2;
        } else {
            layananId = 0;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
