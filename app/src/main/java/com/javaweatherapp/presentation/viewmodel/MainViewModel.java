package com.javaweatherapp.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.javaweatherapp.domain.usecase.GetWeatherUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@HiltViewModel
public class MainViewModel extends ViewModel {

  private final GetWeatherUseCase getWeatherUseCase;
  @Inject
  public MainViewModel(GetWeatherUseCase getWeatherUseCase) {
    this.getWeatherUseCase = getWeatherUseCase;
    // Default constructor for Hilt
  }

  private void executeUseCase() {
    getWeatherUseCase.execute();
  }
}
