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

//Our class extending fragment
public class Tab1 extends Fragment {

    RecyclerView mRecycler1;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        mRecycler1 = view.findViewById(R.id.tab1_recyclerview);
        Log.d("debugMode", "The application stopped after this");

        // this is data fro recycler view
        ItemData itemsData[] = { new ItemData("Help",R.mipmap.ic_launcher),
                new ItemData("Delete",R.mipmap.ic_launcher_round),
                new ItemData("Cloud",R.mipmap.ic_launcher_round),
                new ItemData("Favorite",R.mipmap.ic_launcher_round),
                new ItemData("Like",R.mipmap.ic_launcher_round),
                new ItemData("Rating",R.mipmap.ic_launcher_round)};

        // 2. set layoutManger
        mRecycler1.setLayoutManager(new LinearLayoutManager(getContext()));
        // 3. create an adapter
        MyAdapter mAdapter = new MyAdapter(itemsData);
        // 4. set adapter
        mRecycler1.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        mRecycler1.setItemAnimator(new DefaultItemAnimator());

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return view;
    }
}