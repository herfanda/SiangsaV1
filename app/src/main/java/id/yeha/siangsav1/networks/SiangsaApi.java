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
    public void postRegis(String username, String email, String password, String nohp, String totalFamily, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        PostRegisRequest regisRequest = new PostRegisRequest();
        String url = SiangsaUrl.getInstance().getUrl("postRegistration");

        regisRequest.setUsername(username);
        regisRequest.setEmail(email);
        regisRequest.setPassword(password);
        regisRequest.setNoHp(nohp);
        regisRequest.setTotalFamily(totalFamily);

        JSONObject jsonParameter = regisRequest.generateJsonParameter();
        VolleyJsonObjectRequest request = new VolleyJsonObjectRequest(regisRequest.getMethod(),url,jsonParameter,successListener,errorListener);
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

}
