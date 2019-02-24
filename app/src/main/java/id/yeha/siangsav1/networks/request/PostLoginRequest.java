package id.yeha.siangsav1.networks.request;

import com.android.volley.Request;

public class PostLoginRequest extends BaseRequest {

    private static final String ACTION = "siangsaLogin";
    private String email;
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

        signatureParameters.put("email",email);
        signatureParameters.put("password",password);

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
}
