package id.yeha.siangsav1.networks.request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public void applySignatureParametersToParameters() {
        parameters.putAll(signatureParameters);
    }

    public JSONObject generateJsonParameter(){
        JSONObject parameter = new JSONObject();
        try {
            /*if (getAction() != null && getAction().length() >0){
                parameter.put("action",getAction());
            }*/

            //parameter.put("signature", generateSignature());
            parameter.put("signature", generateSignature());
            applySignatureParametersToParameters();

            Set<Map.Entry<String, Object>> entries = parameters.entrySet();
            for(Map.Entry<String, Object> entry : entries) {
                if (entry.getValue() != null) {
                    parameter.put(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }

            parameter.put("appKey",appKey);
        } catch (Exception e){
            e.printStackTrace();
        }
        return parameter;
    }

    public String generateSignature() {
        populateSignatureParameters();
        Map<String, String> signatureParametersString = new HashMap<>();

        Set<String> keys = signatureParameters.keySet();
        for(String key : keys) {
            Object value = signatureParameters.get(key);
            if (value != null) {
                signatureParametersString.put(key, String.valueOf(value));
            }
        }

        //return SekodeEncryptionTool.generateSignatureStandard(signatureParametersString, AppConfiguration.getInstance().getSignaturePublicKey());

        return String.valueOf(signatureParametersString);
    }

}
