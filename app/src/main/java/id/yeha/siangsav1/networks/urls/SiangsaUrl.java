package id.yeha.siangsav1.networks.urls;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import id.yeha.siangsav1.helper.JSONObjectReader;
import id.yeha.siangsav1.util.AppConfiguration;

public class SiangsaUrl {

    private static final SiangsaUrl ourInstance = new SiangsaUrl();

    public static SiangsaUrl getInstance(){
        return ourInstance;
    }

    private SiangsaUrl(){

    }

    private Context applicationContext;
    private Map<String,String> urlMap;

    public Context getApplicationContext(){
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    public void initiateUrl(){
        if (applicationContext == null){
            throw new NullPointerException("Assign the application context before using SiangsaUrl singleton !");
        } else {
            JSONObject listOfUrls = AppConfiguration.getInstance().getSiangsaUrls(getApplicationContext());
            try {
                urlMap = JSONObjectReader.getInstance().toStringMap(listOfUrls);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    public String getUrl(String functionName){
        return urlMap.get(functionName);
    }

}
