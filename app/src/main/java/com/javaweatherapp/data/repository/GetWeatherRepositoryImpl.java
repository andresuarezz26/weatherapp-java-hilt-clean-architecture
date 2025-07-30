package com.javaweatherapp.data.repository;

import com.javaweatherapp.data.response.GetWeatherItemResponse;
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

  private final GetWeatherService service;
  @Inject
  public GetWeatherRepositoryImpl(GetWeatherService service) {
    this.service = service;
  }

  @Override
  public Single<WeatherInfo> invoke(double lat, double lon, int cnt) {
    // TODO: Do not harcode the api key
    return service.invoke(lat, lon, cnt, "0e8478feb806153aa83809db7b18dc21")
        .subscribeOn(Schedulers.io())
        .map(response -> {
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
    });
  }
}
