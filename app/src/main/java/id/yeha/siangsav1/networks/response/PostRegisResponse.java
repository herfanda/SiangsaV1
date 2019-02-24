package id.yeha.siangsav1.networks.response;

import org.json.JSONObject;

public class PostRegisResponse extends BaseResponse {

    private String name;
    private Integer user_id;
    private String email;
    private String password;
    private String update_at;
    private String created_at;

    public PostRegisResponse(){

    }

    public PostRegisResponse(JSONObject jsonObject){
        super(jsonObject);
        setName(jsonObject.optString("name"));
        setEmail(jsonObject.optString("email"));
        setPassword(jsonObject.optString("password"));
        setUser_id(jsonObject.optInt("user_id"));
        setUpdate_at(jsonObject.optString("update_at"));
        setCreated_at(jsonObject.optString("created_at"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
