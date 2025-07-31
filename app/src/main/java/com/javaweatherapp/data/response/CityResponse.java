package com.javaweatherapp.data.response;

import androidx.annotation.Nullable;

public class CityResponse {
  private String name;
  private String country;

  public CityResponse(String name, String country) {
    this.name = name;
    this.country = country;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Nullable
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
