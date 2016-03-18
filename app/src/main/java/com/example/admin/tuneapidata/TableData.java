package com.example.admin.tuneapidata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 3/15/2016.
 */
public class TableData extends AppCompatActivity {
    NetworkDatabase networkDatabase;
    EditText editText1,editText2,editText3,editText4;
    private static String URL = "https://api.mobileapptracking.com/v2/advertiser/stats/installs/find?sorts=" +
            "created+asc&fields=created,site&start_date=2016-01-06&end_date=2016-03-15&limit=1&api_key=25979496beb9bcb7dec023654f91f920";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apidata);
        networkDatabase = new NetworkDatabase(getApplication());
        editText1 = (EditText) findViewById(R.id.record1);
        editText2 = (EditText)findViewById(R.id.record2);
        editText3 = (EditText)findViewById(R.id.record3);
        editText4 = (EditText)findViewById(R.id.record4);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            for (int j = 0; j < response.length(); j++)
                            {
                                JSONObject throttleJsonData = response.getJSONObject("throttle");
                                String countString = throttleJsonData.getString("count_remaining");
                                editText1.setText(countString);
                                String decisionString= throttleJsonData.getString("decision");
                                editText2.setText(decisionString);
                                String limitString= throttleJsonData.getString("limit");
                                editText3.setText(limitString);
                                String intervalString= throttleJsonData.getString("interval");
                                editText4.setText(intervalString);
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
                        editText1.setText(error.toString());
                        editText2.setText(error.toString());
                        editText3.setText(error.toString());
                        editText4.setText(error.toString());
                    }
                }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}

