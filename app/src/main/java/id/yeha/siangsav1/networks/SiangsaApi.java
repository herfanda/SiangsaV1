package id.yeha.siangsav1.networks;

import com.android.volley.Response;

import org.json.JSONObject;

import id.yeha.siangsav1.networks.request.PostRegisRequest;
import id.yeha.siangsav1.networks.request.VolleyJsonObjectRequest;
import id.yeha.siangsav1.networks.urls.SiangsaUrl;

public class SiangsaApi implements IfaceSiangsaApi {
    @Override
    public void postLogin(String username, String password, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {

    }

    @Override
    public void postLogout(int loginEventId, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {

    }

    @Override
    public void postRegis(String name, String email, String password, String nohp, String address, String lat, String lng, Integer layanan_id, String qty, String status, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        PostRegisRequest regisRequest = new PostRegisRequest();
        String url = SiangsaUrl.getInstance().getUrl("postRegistration");

        regisRequest.setName(name);
        regisRequest.setEmail(email);
        regisRequest.setPassword(password);
        regisRequest.setNoHp(nohp);
        regisRequest.setAddress(address);
        regisRequest.setLat(lat);
        regisRequest.setLng(lng);
        regisRequest.setLayanan_id(layanan_id);
        regisRequest.setQuality(qty);
        regisRequest.setStatus(status);

        JSONObject jsonParameter = regisRequest.generateJsonParameter();
        VolleyJsonObjectRequest request = new VolleyJsonObjectRequest(regisRequest.getMethod(),url,jsonParameter,successListener,errorListener);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }


}
