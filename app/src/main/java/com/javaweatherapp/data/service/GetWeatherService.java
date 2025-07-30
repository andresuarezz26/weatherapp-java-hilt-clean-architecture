package com.javaweatherapp.data.service;

import com.javaweatherapp.data.response.GetWeatherResponse;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;
import retrofit2.Retrofit;

public class GetWeatherService {
  private final Retrofit retrofit;
  @Inject
  public GetWeatherService(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  public Single<GetWeatherResponse> invoke(double lat, double lon, int cnt, String apiKey) {
    return retrofit.create(ApiService.class).getWeather(lat, lon, cnt, apiKey);
  }
}
