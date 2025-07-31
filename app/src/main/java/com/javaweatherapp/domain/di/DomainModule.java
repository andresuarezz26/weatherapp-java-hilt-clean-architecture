package com.javaweatherapp.domain.di;

import com.javaweatherapp.domain.usecase.GetWeatherUseCaseImpl;
import com.javaweatherapp.domain.usecase.GetWeatherUseCase;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
abstract class DomainModule {

  @Binds
  abstract GetWeatherUseCase bindWeatherUseCase(GetWeatherUseCaseImpl weatherUseCase);
}
