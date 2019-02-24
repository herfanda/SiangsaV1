package id.yeha.siangsav1.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.networks.VolleyDatabaseSingleton;
import id.yeha.siangsav1.networks.VolleySingleton;
import id.yeha.siangsav1.networks.urls.SiangsaUrl;
import id.yeha.siangsav1.util.DatabaseNetwork;
import id.yeha.siangsav1.util.StorageHelper;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        initUrl();
        initSplash();


        // Initialize Volley
        initializeVolley();
        //initializeVolleyDb();



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

    private void initializeVolley() {
        Network network;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            network = new BasicNetwork(new HurlStack());
        }
        else {
            // Handle legacy Volley stack
            HurlStack hurlStack = new HurlStack(null, new SSLSocketFactory() {
                @Override
                public String[] getDefaultCipherSuites() {
                    return new String[0];
                }

                @Override
                public String[] getSupportedCipherSuites() {
                    return new String[0];
                }

                @Override
                public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
                    return null;
                }

                @Override
                public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
                    return null;
                }

                @Override
                public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
                    return null;
                }

                @Override
                public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
                    return null;
                }

                @Override
                public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
                    return null;
                }
            });
            network = new BasicNetwork(hurlStack);
        }

        String volleyPath = StorageHelper.getInstance().combinePath(
                new String[] {
                        StorageHelper.getInstance().getCachePath(getApplicationContext()).toString(),
                        "volley"
                }).toString();
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new File(volleyPath)),
                network);
        requestQueue.start();

        VolleySingleton.getInstance().setRequestQueue(requestQueue);
    }

    private void initializeVolleyDb() {
        Network network = new DatabaseNetwork();

        String volleyPath = StorageHelper.getInstance().combinePath(
                new String[] {
                        StorageHelper.getInstance().getCachePath(getApplicationContext()).toString(),
                        "volleydb"
                }).toString();
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new File(volleyPath)),
                network);
        requestQueue.start();

        VolleyDatabaseSingleton.getInstance().setRequestQueue(requestQueue);
    }

}
