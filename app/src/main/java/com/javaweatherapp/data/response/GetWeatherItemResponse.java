package com.javaweatherapp.data.response;

import java.util.List;

public class GetWeatherItemResponse {
  public MainResponse mainResponse;
  public List<WeatherInfo> weather;

  public GetWeatherItemResponse(MainResponse mainResponse, List<WeatherInfo> weather) {
    this.mainResponse = mainResponse;
    this.weather = weather;
  }

  public MainResponse getMainResponse() {
    return mainResponse;
  }

  public void setMainResponse(MainResponse mainResponse) {
    this.mainResponse = mainResponse;
  }

  public List<WeatherInfo> getWeather() {
    return weather;
  }

  public void setWeather(List<WeatherInfo> weather) {
    this.weather = weather;
  }
}
