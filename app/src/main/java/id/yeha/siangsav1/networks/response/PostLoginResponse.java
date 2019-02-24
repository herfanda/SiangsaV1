package id.yeha.siangsav1.networks.response;

import org.json.JSONObject;

public class PostLoginResponse extends BaseResponse {

    private String name;

    public PostLoginResponse() {
    }

    public PostLoginResponse(JSONObject responseObject) {
        super(responseObject);
        setName(responseObject.optString("name"));


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
