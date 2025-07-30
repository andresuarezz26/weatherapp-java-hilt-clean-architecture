package com.javaweatherapp.data.service;

import com.javaweatherapp.data.response.GetWeatherResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

  @GET("data/2.5/forecast")
  Single<GetWeatherResponse> getWeather(@Query("lat") double lat, @Query("lon") double lon,
      @Query("cnt") int cnt, @Query("appid") String apiKey);
}
