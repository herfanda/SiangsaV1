package id.yeha.siangsav1.networks.request;

import com.android.volley.Request;

public class PostRegisRequest extends BaseRequest {

    private static final String ACTION = "activate";
    private String name;
    private String email;
    private String password;
    private String noHp;
    private String address;
    private String lat;
    private String lng;
    private int layanan_id;
    private String quality;
    private String status;
    //private String totalFamily;

    public PostRegisRequest(){
        setMethod(Request.Method.POST);
    }

    @Override
    protected String getAction() {
        return ACTION;
    }

    @Override
    protected void populateSignatureParameters() {
        signatureParameters.put("name",getName());
        signatureParameters.put("email",getEmail());
        signatureParameters.put("password",getPassword());
        signatureParameters.put("noHp",getNoHp());
        signatureParameters.put("address",getAddress());
        signatureParameters.put("address_lat",getLat());
        signatureParameters.put("address_lat",getLng());
        signatureParameters.put("layanan_id",getLayanan_id());
        signatureParameters.put("qty",getQuality());
        signatureParameters.put("status",getStatus());


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getLayanan_id() {
        return layanan_id;
    }

    public void setLayanan_id(int layanan_id) {
        this.layanan_id = layanan_id;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
