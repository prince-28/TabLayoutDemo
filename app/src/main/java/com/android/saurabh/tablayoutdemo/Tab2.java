package com.android.saurabh.tablayoutdemo;

/**
 * Created by Saurabh on 3/23/2018.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
public class Tab2 extends Fragment {

    private static final String URL_DATA = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    private RecyclerView.Adapter adapter;
    private List<HeroesList> herolist;
    RecyclerView mRecyclerview;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2, container, false);

        mRecyclerview = view.findViewById(R.id.tab2_recyclerview);

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes

        herolist = new ArrayList<>();

        loadUrlData();
        return view;
    }

    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("heroes");

                    for(int i = 0; i< jsonArray.length(); i ++){

                        JSONObject jsonObj = jsonArray.getJSONObject(i);

                        HeroesList herolists = new HeroesList(jsonObj.getString("name"), jsonObj.getString("imageurl"));
                        herolist.add(herolists);
                    }

                    adapter = new HeroesAdapter(herolist, getContext());
                    mRecyclerview.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}