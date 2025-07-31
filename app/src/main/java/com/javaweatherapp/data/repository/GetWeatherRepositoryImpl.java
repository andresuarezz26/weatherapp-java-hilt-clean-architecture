package com.javaweatherapp.data.repository;

import android.util.Log;
import com.javaweatherapp.data.local.dao.GetWeatherDao;
import com.javaweatherapp.data.local.entity.WeatherInfoItemEntity;
import com.javaweatherapp.data.response.CityResponse;
import com.javaweatherapp.data.response.GetWeatherItemResponse;
import com.javaweatherapp.data.response.GetWeatherResponse;
import com.javaweatherapp.data.service.GetWeatherService;
import com.javaweatherapp.domain.model.WeatherInfo;
import com.javaweatherapp.domain.model.WeatherInfoItem;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.inject.Inject;

import static com.javaweatherapp.data.utils.TemperatureConverterUtils.kelvinToFahrenheit;

public class GetWeatherRepositoryImpl implements GetWeatherRepository {

  private final long CACHE_DURATION_MS = 20 * 60 * 1000; // 20 minutes
  private final SimpleDateFormat API_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  private final SimpleDateFormat DISPLAY_FORMAT = new SimpleDateFormat("MMMM dd, yyyy HH:mm", Locale.US);


  private final GetWeatherService service;
  private final GetWeatherDao dao;
  @Inject
  public GetWeatherRepositoryImpl(GetWeatherService service, GetWeatherDao dao) {
    this.service = service;
    this.dao = dao;
  }

  @Override
  public Single<WeatherInfo> invoke(double lat, double lon, int cnt) {
    final long now = System.currentTimeMillis();

    return dao.getWeatherItemsByLatitudeAndLongitude(lat, lon)
        .subscribeOn(Schedulers.io())
        .flatMap(list -> {
          if (list != null && !list.isEmpty() && now - list.get(0).lastUpdated < CACHE_DURATION_MS) {
              final WeatherInfo weatherInfo = mapEntitiesToDomain(list);
              return Single.just(weatherInfo);
          }

          /** TODO: Move this to local.properties
           *  To facilitate the set up of the project this api key will be hardcoded here.
           *  For a production application, this must be stored on local.properties and accessed via
           *  BuildConfig and not be submitted to git.
           */
          return service.invoke(lat, lon, cnt, "0e8478feb806153aa83809db7b18dc21")
              .subscribeOn(Schedulers.io())
              .map(response -> {

                final CityResponse city = response.getCity();
                String cityName = "";
                String country = "";
                if(city != null) {
                  cityName = city.getName();
                  country = city.getCountry();
                }
                List<WeatherInfoItemEntity> entities =
                    mapResponseToEntities(response, now, cityName, country, lat, lon);
                dao.deleteWeatherItemsByCityAndCountry(cityName, country);
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
    for ( int i = 0; i < entities.size(); i++) {
      final WeatherInfoItemEntity entity = entities.get(i);
      weatherInfo.setCityName(entity.cityName);
      weatherInfo.setCountry(entity.country);
      WeatherInfoItem item = new WeatherInfoItem();
      item.setTemp(kelvinToFahrenheit(entity.temp));
      item.setTempMax(kelvinToFahrenheit(entity.tempMax));
      item.setTempMin(kelvinToFahrenheit(entity.tempMin));
      item.setDay(getFormattedDayFromDate(i, entity.dateTimeText));
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
        entity.dateTimeText = item.getDateTimeText();
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

    final CityResponse city = response.getCity();
    String cityName = "";
    String country = "";
    if(city != null) {
      cityName = city.getName();
      country = city.getCountry();
    }
    weatherInfo.setCityName(cityName);
    weatherInfo.setCountry(country);
    List<WeatherInfoItem> weatherInfoList = new ArrayList<>();

    if(response.getList() == null) {
      return weatherInfo;
    }
    final List<GetWeatherItemResponse> responseList = response.getList();
    for (int i = 0; i < responseList.size(); i++) {
      GetWeatherItemResponse item = responseList.get(i);
      WeatherInfoItem weatherInfoItem = new WeatherInfoItem();
      weatherInfoItem.setDay(getFormattedDayFromDate(i, item.getDateTimeText()));
      weatherInfoList.add(weatherInfoItem);

      weatherInfoItem.setTemp(kelvinToFahrenheit(item.getMain().getTemp()));
      weatherInfoItem.setTempMax(kelvinToFahrenheit(item.getMain().getTemp_max()));
      weatherInfoItem.setTempMin(kelvinToFahrenheit(item.getMain().getTemp_min()));
      weatherInfoList.add(weatherInfoItem);
    }
    weatherInfo.setWeatherInfoList(weatherInfoList);

    return weatherInfo;
  }

    private String getFormattedDayFromDate(int index, String dateTimeText) {
      if (index == 0) {
        return "Now";
      } else {
        try {
          Date date = API_FORMAT.parse(dateTimeText);
          if(date != null) {
            return DISPLAY_FORMAT.format(date);
          } else {
            return "";
          }
        } catch (ParseException e) {
          Log.e("Error", Objects.requireNonNull(e.getMessage()));
          return "";// fallback if parse fails
        }
      }
  }
}


