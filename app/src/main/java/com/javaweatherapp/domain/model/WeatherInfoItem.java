package com.javaweatherapp.domain.model;

public class WeatherInfoItem {

  private double temp;
  private double tempMin;
  private double tempMax;
  private int humidity;
  private String day;
  public double getTemp() {
    return temp;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public double getTempMin() {
    return tempMin;
  }

  public void setTempMin(double tempMin) {
    this.tempMin = tempMin;
  }

  public double getTempMax() {
    return tempMax;
  }

  public void setTempMax(double tempMax) {
    this.tempMax = tempMax;
  }

  public int getHumidity() {
    return humidity;
  }

  public void setHumidity(int humidity) {
    this.humidity = humidity;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }
}
