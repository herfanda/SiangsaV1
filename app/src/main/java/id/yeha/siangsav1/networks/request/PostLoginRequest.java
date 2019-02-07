package id.yeha.siangsav1.networks.request;

import com.android.volley.Request;

public class PostLoginRequest extends BaseRequest {

    private static final String ACTION = "siangsaLogin";
    private String username;
    private String password;

    public PostLoginRequest(){
        setMethod(Request.Method.POST);
    }
    @Override
    protected String getAction() {
        return ACTION;
    }

    @Override
    protected void populateSignatureParameters() {

        signatureParameters.put("username",username);
        signatureParameters.put("password",password);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
