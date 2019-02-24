package id.yeha.siangsav1.networks;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class VolleyDatabaseSingleton {

    private static final VolleyDatabaseSingleton ourInstance = new VolleyDatabaseSingleton();
    private static final int TIMEOUT = 15000;

    private RequestQueue requestQueue;

    public static VolleyDatabaseSingleton getInstance() {
        return ourInstance;
    }

    private VolleyDatabaseSingleton() {
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            throw new NullPointerException("Please set the request queue before using the Volley Singleton!");
        }
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueueCustomTimeout(req, TIMEOUT);
    }

    public <T> void addToRequestQueueCustomTimeout(Request<T> req, int customTimeout) {
        DefaultRetryPolicy currentPolicy = (DefaultRetryPolicy) req.getRetryPolicy();
        DefaultRetryPolicy policy = new DefaultRetryPolicy(customTimeout,
                currentPolicy.getCurrentRetryCount(),
                currentPolicy.getBackoffMultiplier());
        req.setRetryPolicy(policy);

        getRequestQueue().add(req);
    }

}
