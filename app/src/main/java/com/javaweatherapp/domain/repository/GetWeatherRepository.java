package com.javaweatherapp.domain.repository;

import com.javaweatherapp.domain.model.WeatherInfo;
import io.reactivex.rxjava3.core.Single;

public interface GetWeatherRepository {

  Single<WeatherInfo> invoke(double lat, double lon, int cnt);

}
