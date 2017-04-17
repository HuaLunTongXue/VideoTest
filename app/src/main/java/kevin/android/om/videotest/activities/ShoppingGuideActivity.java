package kevin.android.om.videotest.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import kevin.android.om.videotest.R;
import kevin.android.om.videotest.VActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShoppingGuideActivity extends VActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_guide);
        (findViewById(R.id.mainBgId)).setBackgroundResource(R.color.mainBg);


//        RequestQueue mQueue = Volley.newRequestQueue(this);
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("jsonrpc", "2.0");
//            jsonObject.put("method", "call");
//            jsonObject.put("params", "{\"method\":\"vmc_ad_list\",\"machine_id\":\"6\",\"app_version\":\"0.5.0\"}");
//            jsonObject.put("id", "3");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST,
//                                                                   TEST_URL,
//                                                                   jsonObject,
//                                                                   new Response.Listener<JSONObject>() {
//                                                                       @Override
//                                                                       public void onResponse(JSONObject jsonObject) {
//                                                                           showInfo.setText(jsonObject.toString());
//                                                                       }
//                                                                   }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                showInfo.setText("error="+volleyError.toString());
//            }
//        });
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                                                        TEST_URL,
//                                                        new Response.Listener<String>() {
//                                                            @Override
//                                                            public void onResponse(String s) {
//                                                                showInfo.setText(s);
//                                                            }
//                                                        },
//                                                        new Response.ErrorListener() {
//                                                            @Override
//                                                            public void onErrorResponse(VolleyError volleyError) {
//                                                                showInfo.setText("error="+volleyError.toString());
//
//                                                            }
//                                                        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("Content-Type", "application/json; charset=UTF-8");
//                return map;
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("jsonrpc", "2.0");
//                map.put("method", "call");
//                map.put("params", "{\"method\":\"vmc_ad_list\",\"machine_id\":\"6\",\"app_version\":\"0.5.0\"}");
//                map.put("id", "3");
//                return map;
//            }
//
//            @Override
//            protected String getParamsEncoding() {
//                return super.getParamsEncoding();
//            }
//        };
//
//        mQueue.add(jsonArrayRequest);



    }





}
