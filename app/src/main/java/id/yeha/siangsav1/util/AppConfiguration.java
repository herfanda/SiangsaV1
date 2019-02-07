package id.yeha.siangsav1.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.yeha.siangsav1.R;
import id.yeha.siangsav1.helper.JsonFileHelper;
import id.yeha.siangsav1.networks.IfaceSiangsaApi;
import id.yeha.siangsav1.networks.SiangsaApi;

public class AppConfiguration {

    private static final AppConfiguration ourInstance = new AppConfiguration();

    private static final String appKey = "01234567890siangsakabupatenjepara01234567890";

    public static AppConfiguration getInstance(){
        return ourInstance;
    }

    public IfaceSiangsaApi getSiangsaApi(){
        return new SiangsaApi();
    }

    public JSONObject getSiangsaUrls(Context context){
        Integer siangsaUrl = R.raw.siangsa_url;
        try {
            return JsonFileHelper.getInstance().readJsonFromResource(context,siangsaUrl);
        }catch (IOException e){
            e.printStackTrace();
            return null;

        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getAppKey(){
        return appKey;
    }


}
