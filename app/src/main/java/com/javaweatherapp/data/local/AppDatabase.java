package com.javaweatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.javaweatherapp.data.local.dao.GetWeatherDao;
import com.javaweatherapp.data.local.entity.WeatherInfoItemEntity;

@Database(entities = { WeatherInfoItemEntity.class }, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  public abstract GetWeatherDao getWeatherDao();
}
