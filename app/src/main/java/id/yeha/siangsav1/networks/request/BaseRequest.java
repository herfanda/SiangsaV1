package id.yeha.siangsav1.networks.request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.yeha.siangsav1.util.AppConfiguration;

public abstract class BaseRequest {
    protected Map<String , Object> parameters;
    protected Map<String , Object> signatureParameters;
    protected String appKey;
    protected int method;

    public BaseRequest(){
        parameters = new HashMap<>();
        signatureParameters = new HashMap<>();
        appKey = AppConfiguration.getInstance().getAppKey();
    }

    protected abstract String getAction();
    protected abstract void populateSignatureParameters();

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public JSONObject generateJsonParameter(){
        JSONObject parameter = new JSONObject();
        try {
            if (getAction() != null && getAction().length() >0){
                parameter.put("action",getAction());
            }

            parameter.put("appKey",appKey);
        } catch (Exception e){
            e.printStackTrace();
        }
        return parameter;
    }

}
