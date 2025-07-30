package com.javaweatherapp.data.response;

import java.util.List;

public class GetWeatherItemResponse {
  private MainResponse main;
  private List<WeatherInfoResponse> weather;

  public GetWeatherItemResponse(MainResponse mainResponse, List<WeatherInfoResponse> weather) {
    this.main = mainResponse;
    this.weather = weather;
  }

  public MainResponse getMain() {
    return main;
  }

  public void setMain(MainResponse main) {
    this.main = main;
  }

  public List<WeatherInfoResponse> getWeather() {
    return weather;
  }

  public void setWeather(List<WeatherInfoResponse> weather) {
    this.weather = weather;
  }
}
