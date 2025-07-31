package com.javaweatherapp.domain.usecase;

import com.javaweatherapp.domain.model.WeatherInfo;
import io.reactivex.rxjava3.core.Single;

public interface GetWeatherUseCase {

   Single<WeatherInfo> execute(GetWeatherUseCaseImpl.Params params);
}
