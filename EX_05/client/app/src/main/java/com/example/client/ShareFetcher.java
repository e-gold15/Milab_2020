package com.example.client;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import static com.android.volley.Response.*;

import org.json.JSONException;
import org.json.JSONObject;

public class ShareFetcher {
    private RequestQueue _queue;
    private final static String REQUEST_URL = "http://10.0.2.2:8080/sharePrice";

    public class ShareResponse {
        public boolean isError;
        public String shareName;
        public String price;

        public ShareResponse(boolean isError, String shareName, String price) {
            this.isError = isError;
            this.shareName = shareName;
            this.price = price;
        }
    }

    public interface shareReasponseListener {
        public void onResponse(ShareResponse response);
        }

        public ShareFetcher(Context context) {
            _queue = Volley.newRequestQueue(context);
        }

        private ShareResponse createErrorResponse() {
            return new ShareResponse(true, null, null);
        }

        public void dispatchRequest(final String shareName, final String token, final shareReasponseListener listener) {
            JSONObject postBody = new JSONObject();
            try {
                postBody.put("shareName", shareName);
                postBody.put("token", token);
            }
            catch (JSONException e) {
                listener.onResponse(createErrorResponse());
                return;
            }

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, REQUEST_URL, postBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                ShareResponse res = new ShareResponse(false, shareName, response.getJSONObject("Global Quote").getString("05. price"));
                                listener.onResponse(res);
                            }
                            catch (JSONException e) {
                                listener.onResponse(createErrorResponse());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onResponse(createErrorResponse());
                }
            });

            _queue.add(req);
        }
    }