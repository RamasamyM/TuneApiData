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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    NetworkDatabase networkDatabase;
    TextView  mTextView1,mTextView2,mTextView3,mTextView4;
    private static String URL = "https://api.mobileapptracking.com/v2/advertiser/stats/installs/find?sorts=" +
            "created+asc&fields=created,site&start_date=2016-01-06&end_date=2016-03-15&limit=1&api_key=25979496beb9bcb7dec023654f91f920";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkDatabase = new NetworkDatabase(getApplication());
        mTextView1 = (TextView)findViewById(R.id.record1);
        mTextView2 = (TextView)findViewById(R.id.record2);
        mTextView3 = (TextView)findViewById(R.id.record3);
        mTextView4 = (TextView)findViewById(R.id.record4);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            for (int j = 0; j < response.length(); j++)
                            {
                                JSONObject throttleJsonData = response.getJSONObject("throttle");
                                String countString = throttleJsonData.getString("count_remaining");
                                mTextView1.setText(countString);
                                String decisionString= throttleJsonData.getString("decision");
                                mTextView2.setText(decisionString);
                                String limitString= throttleJsonData.getString("limit");
                                mTextView3.setText(limitString);
                                String intervalString= throttleJsonData.getString("interval");
                                mTextView4.setText(intervalString);
                            }

                           /* ContentValues values = new ContentValues();
                            values.put("record_name", string);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
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

