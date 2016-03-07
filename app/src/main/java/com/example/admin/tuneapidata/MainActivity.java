package com.example.admin.tuneapidata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    NetworkDatabase networkDatabase;
    TextView  mTextView1;
    private static String URL = "https://api.mobileapptracking.com/v2/advertiser/stats/installs/count.json?start_date=2016-03-01+00:00:00+Indian/" +
            "Chagos&end_date=2016-03-05+00:00:00+Indian/Chagos&session_token=c38f4585a26c1777e238d9ea571f9c381bfd6222&session_token=f47ff326482520410909fce4f841dd6b85384aea";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkDatabase = new NetworkDatabase(getApplication());
        mTextView1 = (TextView)findViewById(R.id.record1);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    String string;

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray = response.getJSONArray("throttle");
                            for (int j = 0; j < jsonArray.length(); j++) {

                                string = jsonArray.getJSONObject(j).getString("limit");
                                mTextView1.setText(string);
                            }

                            /*ContentValues values = new ContentValues();
                            values.put("record_name", string);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView1.setText(error.toString());
                    }
                }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}

