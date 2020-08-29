package com.muniba3.wheatherapplication.Retrofit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=92756c24107bc39dd0a7541f66ba55c5")
    Call<Example> getWeatherData(@Query("q")String name);
}
//weather?&appid=439d4b804bc8187953eb36d2a8c26a02
