package com.muniba3.wheatherapplication.Retrofit;

import com.google.gson.annotations.SerializedName;


public class Example {
    @SerializedName("main")
    public Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
