package id.yeha.siangsav1.networks.request;

import com.android.volley.Request;

public class PostRegisRequest extends BaseRequest {

    private static final String ACTION = "siangsaRegistration";
    private String username;
    private String email;
    private String password;
    private String noHp;
    private String totalFamily;

    public PostRegisRequest(){
        setMethod(Request.Method.POST);
    }

    @Override
    protected String getAction() {
        return ACTION;
    }

    @Override
    protected void populateSignatureParameters() {
        signatureParameters.put("username",username);
        signatureParameters.put("email",email);
        signatureParameters.put("password",password);
        signatureParameters.put("noHp",noHp);
        signatureParameters.put("totalFamily",totalFamily);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getTotalFamily() {
        return totalFamily;
    }

    public void setTotalFamily(String totalFamily) {
        this.totalFamily = totalFamily;
    }
}
