package com.javaweatherapp.domain.model;

import java.util.List;

public class WeatherInfo {

  private String cityName;

  private String country;

  private List<WeatherInfoItem> weatherInfoList;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public List<WeatherInfoItem> getWeatherInfoList() {
    return weatherInfoList;
  }

  public void setWeatherInfoList(List<WeatherInfoItem> weatherInfoList) {
    this.weatherInfoList = weatherInfoList;
  }
}
