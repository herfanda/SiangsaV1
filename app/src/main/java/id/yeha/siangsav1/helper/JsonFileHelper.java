package id.yeha.siangsav1.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.amazonaws.util.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonFileHelper {

    private static final JsonFileHelper ourInstance = new JsonFileHelper();

    public static JsonFileHelper getInstance() {
        return ourInstance;
    }

    private JsonFileHelper() {
    }

    public JSONObject readJsonFromFile(Context context, String assetName) throws IOException, JSONException {
        AssetManager am = context.getAssets();
        InputStream is = am.open(assetName);
        String content = IOUtils.toString(is);
        return new JSONObject(content);
    }

    public JSONObject readJsonFromResource(Context context, int resId) throws IOException, JSONException {
        InputStream is = context.getResources().openRawResource(resId);
        String content = IOUtils.toString(is);
        return new JSONObject(content);
    }

    public String readString(JSONObject jsonObject, String attributeName) {
        try {
            String attribute = jsonObject.getString(attributeName);
            return attribute;
        } catch (JSONException e) {
            Log.i(getClass().getCanonicalName(), e.getLocalizedMessage());
            return null;
        }
    }

    public Boolean readBoolean(JSONObject jsonObject, String attributeName) {
        try {
            Boolean attribute = jsonObject.getBoolean(attributeName);
            return attribute;
        } catch (JSONException e) {
            Log.i(getClass().getCanonicalName(), e.getLocalizedMessage());
            return null;
        }
    }

    public JSONArray readJsonArray(JSONObject jsonObject, String attributeName) {
        try {
            return jsonObject.getJSONArray(attributeName);
        } catch (JSONException e) {
            Log.i(getClass().getCanonicalName(), e.getLocalizedMessage());
            return null;
        }
    }

    public Long readLong(JSONObject jsonObject, String attributeName) {
        try {
            return jsonObject.getLong(attributeName);
        } catch (JSONException e) {
            Log.i(getClass().getCanonicalName(), e.getLocalizedMessage());
            return null;
        }

    }

}
