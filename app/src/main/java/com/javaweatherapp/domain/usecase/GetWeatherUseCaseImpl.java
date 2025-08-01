package com.javaweatherapp.domain.usecase;

import com.javaweatherapp.domain.model.WeatherInfo;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;

@ViewModelScoped
public class GetWeatherUseCaseImpl implements GetWeatherUseCase {

  public static class Params {
    public final double lat;
    public final double lon;
    public final int cnt;

    public Params(double lat, double lon, int cnt) {
      this.lat = lat;
      this.lon = lon;
      this.cnt = cnt;
    }
  }

  private final GetWeatherRepository repository;

  @Inject
  public GetWeatherUseCaseImpl(GetWeatherRepository repository){
    this.repository = repository;
  }

  @Override
  public Single<WeatherInfo> execute(Params params)  {
    return repository.invoke(params.lat, params.lon, params.cnt);
  }
}
