package id.yeha.siangsav1.model;

import android.databinding.BaseObservable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.yeha.siangsav1.helper.JSONArrayReader;
import id.yeha.siangsav1.helper.JSONObjectReader;

public abstract class BaseModel extends BaseObservable {

    protected Integer readInteger(JSONObject jsonObject, String attributeName) {
        return JSONObjectReader.getInstance().readInteger(jsonObject, attributeName);
    }

    protected String readString(JSONObject jsonObject, String attributeName) {
        return JSONObjectReader.getInstance().readString(jsonObject, attributeName);
    }

    protected Boolean readBoolean(JSONObject jsonObject, String attributeName) {
        return JSONObjectReader.getInstance().readBoolean(jsonObject, attributeName);
    }

    protected Boolean readBooleanYN(JSONObject jsonObject, String attributeName) {
        String booleanYN = JSONObjectReader.getInstance().readString(jsonObject, attributeName);
        if (booleanYN != null) {
            return booleanYN.equals("Y");
        } else {
            return false;
        }
    }

    protected JSONArray readJsonArray(JSONObject jsonObject, String attributeName) {
        return JSONObjectReader.getInstance().readJsonArray(jsonObject, attributeName);
    }

    protected JSONObject readJsonObject(JSONObject jsonObject, String attributeName) {
        return JSONObjectReader.getInstance().readJsonObject(jsonObject, attributeName);
    }

    protected JSONObject readJsonObject(JSONArray jsonArray, int index) {
        return JSONArrayReader.getInstance().readJsonObject(jsonArray, index);
    }

    protected Double readDouble(JSONObject jsonObject, String attributeName) {
        return JSONObjectReader.getInstance().readDouble(jsonObject, attributeName);
    }

    public abstract JSONObject getAsJsonObject();

    protected JSONObject populateJson(JSONObject input, String name, Object value) {
        try {
            input.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return input;
    }

}
