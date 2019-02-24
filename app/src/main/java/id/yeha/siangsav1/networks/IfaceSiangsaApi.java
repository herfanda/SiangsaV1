package id.yeha.siangsav1.networks;

import com.android.volley.Response;

import org.json.JSONObject;

public interface IfaceSiangsaApi {

    void postLogin(String username, String password, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener);

    void postLogout(int loginEventId,Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener);

    void postRegis(String username, String email, String password, String nohp,String address,String lat,String lng, Integer layanan_id,String qty, String status, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener);
}
