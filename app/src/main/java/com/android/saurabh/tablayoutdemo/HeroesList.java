package com.android.saurabh.tablayoutdemo;

/**
 * Created by Saurabh on 3/28/2018.
 */

public class HeroesList {

    public String gettitle() {
        return title;
    }

    public String gethero_url() {
        return hero_url;
    }

    private String title;
    private String hero_url;

    public HeroesList(String title, String hero_url) {
        this.title = title;
        this.hero_url = hero_url;
    }


}
