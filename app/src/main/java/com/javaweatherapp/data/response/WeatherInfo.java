package com.javaweatherapp.data.response;

public class WeatherInfo {
  public String main;
  public String description;

  public WeatherInfo(String main, String description) {
    this.main = main;
    this.description = description;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
