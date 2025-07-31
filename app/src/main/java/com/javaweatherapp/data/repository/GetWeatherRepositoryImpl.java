package com.javaweatherapp.data.repository;

import com.javaweatherapp.data.local.dao.GetWeatherDao;
import com.javaweatherapp.data.local.entity.WeatherInfoItemEntity;
import com.javaweatherapp.data.response.GetWeatherItemResponse;
import com.javaweatherapp.data.response.GetWeatherResponse;
import com.javaweatherapp.data.service.GetWeatherService;
import com.javaweatherapp.domain.model.WeatherInfo;
import com.javaweatherapp.domain.model.WeatherInfoItem;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class GetWeatherRepositoryImpl implements GetWeatherRepository {

  private final long CACHE_DURATION_MS = 20 * 60 * 1000; // 20 minutes

  private final GetWeatherService service;
  private final GetWeatherDao dao;
  @Inject
  public GetWeatherRepositoryImpl(GetWeatherService service, GetWeatherDao dao) {
    this.service = service;
    this.dao = dao;
  }

  @Override
  public Single<WeatherInfo> invoke(double lat, double lon, int cnt) {
    long now = System.currentTimeMillis();

    return dao.getWeatherItemsByLatitudeAndLongitude(lat, lon)
        .subscribeOn(Schedulers.io())
        .flatMap(list -> {
          if (list != null && !list.isEmpty() && now - list.get(0).lastUpdated < CACHE_DURATION_MS) {
              // Map DB cache to domain and return
              WeatherInfo weatherInfo = mapEntitiesToDomain(list);
              return Single.just(weatherInfo);
          }

          // TODO: Remove the harcoded API key here
          return service.invoke(lat, lon, cnt, "0e8478feb806153aa83809db7b18dc21")
              .subscribeOn(Schedulers.io())
              .map(response -> {
                final String city = response.getCity().getName();
                final String country = response.getCity().getCountry();
                List<WeatherInfoItemEntity> entities =
                    mapResponseToEntities(response, now, city, country, lat, lon);
                dao.deleteWeatherItemsByCityAndCountry(city, country); // Clear old cache
                dao.insertWeatherItems(entities);
                return mapResponseToDomain(response);
              });
        });
  }

  // Helper: map DB entities to WeatherInfo domain object
  private WeatherInfo mapEntitiesToDomain(
      List<WeatherInfoItemEntity> entities) {
    WeatherInfo weatherInfo = new WeatherInfo();

    List<WeatherInfoItem> items = new ArrayList<>();
    for (WeatherInfoItemEntity entity : entities) {
      weatherInfo.setCityName(entity.cityName);
      weatherInfo.setCountry(entity.country);
      WeatherInfoItem item = new WeatherInfoItem();
      item.setTemp(entity.temp);
      item.setTempMax(entity.tempMax);
      item.setTempMin(entity.tempMin);
      items.add(item);
    }
    weatherInfo.setWeatherInfoList(items);
    return weatherInfo;
  }

  // Helper: map API response to DB cacheable entities
  private List<WeatherInfoItemEntity> mapResponseToEntities(
      GetWeatherResponse response, long timestamp, String cityName, String country, double lat, double lon) {
    List<WeatherInfoItemEntity> entities = new ArrayList<>();
    if (response.getList() != null) {
      for (GetWeatherItemResponse item : response.getList()) {
       WeatherInfoItemEntity entity =
            new WeatherInfoItemEntity();
        entity.temp = item.getMain().getTemp();
        entity.tempMax = item.getMain().getTemp_max();
        entity.tempMin = item.getMain().getTemp_min();
        entity.lastUpdated = timestamp;
        entity.cityName = cityName;
        entity.country = country;
        entity.latitute = lat;
        entity.longitude = lon;
        entities.add(entity);
      }
    }
    return entities;
  }

  private WeatherInfo mapResponseToDomain(GetWeatherResponse response) {
    WeatherInfo weatherInfo = new WeatherInfo();
    weatherInfo.setCityName(response.getCity().getName());
    weatherInfo.setCountry(response.getCity().getCountry());
    List<WeatherInfoItem> weatherInfoList = new ArrayList<>();
    for(GetWeatherItemResponse item : response.getList()) {
      WeatherInfoItem weatherInfoItem = new WeatherInfoItem();
      weatherInfoItem.setTemp(item.getMain().getTemp());
      weatherInfoItem.setTempMax(item.getMain().getTemp_max());
      weatherInfoItem.setTempMin(item.getMain().getTemp_min());
      weatherInfoList.add(weatherInfoItem);
    }
    weatherInfo.setWeatherInfoList(weatherInfoList);

    return weatherInfo;
  }
}
