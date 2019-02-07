package id.yeha.siangsav1.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.yeha.siangsav1.model.BaseModel;

public class JSONArrayReader {

    private static final JSONArrayReader ourInstance = new JSONArrayReader();

    public static JSONArrayReader getInstance() {
        return ourInstance;
    }

    private JSONArrayReader() {
    }

    public JSONObject readJsonObject(JSONArray jsonArray, int index) {
        try {
            return jsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<JSONObject> jsonArrayToList(JSONArray jsonArray) {
        List<JSONObject> list = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public JSONObject listToJsonArrayForServer(List<? extends BaseModel> list) {

        JSONObject result = new JSONObject();
        if (SimpleValidate.isCollectionNotEmpty(list)) {
            try {
                JSONArray jsonArray = new JSONArray();
                for(BaseModel baseModel : list) {
                    if (baseModel != null) {
                        jsonArray.put(baseModel.getAsJsonObject());
                    }
                }
                result.put("data", jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONArray jsonArray = new JSONArray();
                result.put("data", jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public JSONArray listToJsonArray(List<? extends BaseModel> list) {
        JSONArray jsonArray = new JSONArray();
        for(BaseModel baseModel: list) {
            jsonArray.put(baseModel.getAsJsonObject());
        }
        return jsonArray;
    }

}
