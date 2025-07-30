package com.javaweatherapp.data.response;

import java.util.List;

public class GetWeatherResponse {
  private List<GetWeatherItemResponse> list;
  private CityResponse city;

  public GetWeatherResponse(List<GetWeatherItemResponse> list, CityResponse cityResponse) {
    this.list = list;
    this.city = cityResponse;
  }

  public List<GetWeatherItemResponse> getList() {
    return list;
  }

  public void setList(List<GetWeatherItemResponse> list) {
    this.list = list;
  }

  public CityResponse getCity() {
    return city;
  }

  public void setCity(CityResponse city) {
    this.city = city;
  }
}
