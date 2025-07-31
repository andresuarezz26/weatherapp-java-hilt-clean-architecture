package com.javaweatherapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_info_items")
public class WeatherInfoItemEntity {
  @PrimaryKey(autoGenerate = true)
  public long id;

  public long weatherInfoId; // parent ID reference
  public double temp;
  public double tempMin;
  public double tempMax;
  public int humidity;

  public double latitute;

  public double longitude;

  public String cityName;
  public String country;

  public long lastUpdated;
}
