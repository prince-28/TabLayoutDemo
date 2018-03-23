package com.android.saurabh.tablayoutdemo;

/**
 * Created by Saurabh on 3/23/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

//Our class extending fragment
public class Tab1 extends Fragment {

    RecyclerView mRecycler1;
    ProgressBar progressBar;

    //the hero list where we will store all the hero objects after parsing json
    List<ItemData> heroList;

    private static  final String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        mRecycler1 = view.findViewById(R.id.tab1_recyclerview);

        //getting the progressbar
        progressBar = view.findViewById (R.id.progressBar);

        heroList = new ArrayList<>();

        Log.d("debugMode", "The application stopped after this");

//        // this is data fro recycler view
//        ItemData itemsData[] = { new ItemData("Help",R.mipmap.ic_launcher),
//                new ItemData("Delete",R.mipmap.ic_launcher_round),
//                new ItemData("Cloud",R.mipmap.ic_launcher_round),
//                new ItemData("Favorite",R.mipmap.ic_launcher_round),
//                new ItemData("Like",R.mipmap.ic_launcher_round),
//                new ItemData("Rating",R.mipmap.ic_launcher_round)};

        callApi();

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return view;
    }

    private void callApi(){


        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);



                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("heroes");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                ItemData hero = new ItemData(heroObject.getString("name"), heroObject.getString("imageurl"));

                                //adding the hero to herolist
                                heroList.add(hero);
                            }

                            //creating custom adapter object
//                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(heroList, getContext());

                            //adding the adapter to listview
//                            listView.setAdapter(adapter);

                            // 2. set layoutManger
                            mRecycler1.setLayoutManager(new LinearLayoutManager(getContext()));
                            // 3. create an adapter
                            MyAdapter mAdapter = new MyAdapter(heroList);
                            // 4. set adapter
                            mRecycler1.setAdapter(mAdapter);
                            // 5. set item animator to DefaultAnimator
                            mRecycler1.setItemAnimator(new DefaultItemAnimator());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
