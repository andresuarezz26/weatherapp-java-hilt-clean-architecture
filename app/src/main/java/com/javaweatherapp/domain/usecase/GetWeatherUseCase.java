package com.javaweatherapp.domain.usecase;

import android.util.Log;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import dagger.hilt.android.scopes.ViewModelScoped;
import javax.inject.Inject;

@ViewModelScoped
public class GetWeatherUseCase {

  private final GetWeatherRepository repository;

  @Inject
  public GetWeatherUseCase(GetWeatherRepository repository){
    this.repository = repository;
  }

  public void execute() {
    // TODO: Implement the use case
    Log.d("GetWeatherUseCase", "execute");
  }
}
