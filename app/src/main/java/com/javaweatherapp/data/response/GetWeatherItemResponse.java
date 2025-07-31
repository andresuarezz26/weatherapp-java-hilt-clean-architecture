package com.javaweatherapp.data.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetWeatherItemResponse {
  private MainResponse main;
  private List<WeatherInfoResponse> weather;

  @SerializedName("dt_txt")
  private String dateTimeText;

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

  public String getDateTimeText() {
    return dateTimeText;
  }

  public void setDateTimeText(String dateTimeText) {
    this.dateTimeText = dateTimeText;
  }
}
