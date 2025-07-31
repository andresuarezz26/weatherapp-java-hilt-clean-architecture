package com.javaweatherapp.data.response;

import com.google.gson.annotations.SerializedName;

public class WeatherInfoResponse {
  private String main;

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }
}
