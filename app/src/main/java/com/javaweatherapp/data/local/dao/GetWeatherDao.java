package com.javaweatherapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.javaweatherapp.data.local.entity.WeatherInfoItemEntity;
import java.util.List;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface GetWeatherDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertWeatherItems(List<WeatherInfoItemEntity> items);

  @Query("SELECT * FROM weather_info_items")
  Single<List<WeatherInfoItemEntity>> getAllWeatherItems();

  @Query("SELECT * FROM weather_info_items WHERE latitute = :lat AND longitude = :lon")
  Single<List<WeatherInfoItemEntity>> getWeatherItemsByLatitudeAndLongitude(double lat, double lon);

  @Query("DELETE FROM weather_info_items WHERE cityName = :city AND country = :country")
  void deleteWeatherItemsByCityAndCountry(String city, String country);
}
