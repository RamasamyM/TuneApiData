package com.example.admin.tuneapidata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by Admin on 3/4/2016.
 */
public class NetworkCall  extends Fragment {
    public NetworkCall() {

    }
    RequestQueue requestQueue;
    NetworkDatabase networkDatabase;

    private static String URL = "https://api.mobileapptracking.com/v2/advertiser/stats/installs/count.json?start_date=2016-02-28+00:00:00+Indian/Chagos&end_date=2016-03-03+00:00:00+" +
            "Indian/Chagos&_=1457065376547&session_token=48446916f9f50cc2854d2586a53ef0b34ee046ef";
    private TextView mTextView1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        networkDatabase = new NetworkDatabase(getActivity());
        mTextView1 = (TextView) view.findViewById(R.id.record1);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    String string;

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray = response.getJSONArray("throttle");
                            for (int j = 0; j < jsonArray.length(); j++) {

                                string = jsonArray.getJSONObject(j).getString("count_remaining");
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
        requestQueue.add(request);
    }
}


