package com.javaweatherapp.data.response;

import java.util.List;

public class GetWeatherResponse {
  public List<GetWeatherItemResponse> list;
  public CityResponse cityResponse;

  public GetWeatherResponse(List<GetWeatherItemResponse> list, CityResponse cityResponse) {
    this.list = list;
    this.cityResponse = cityResponse;
  }

  public List<GetWeatherItemResponse> getList() {
    return list;
  }

  public void setList(List<GetWeatherItemResponse> list) {
    this.list = list;
  }

  public CityResponse getCityResponse() {
    return cityResponse;
  }

  public void setCityResponse(CityResponse cityResponse) {
    this.cityResponse = cityResponse;
  }
}
