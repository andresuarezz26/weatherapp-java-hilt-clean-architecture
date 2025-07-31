package com.javaweatherapp.domain.di;

import com.javaweatherapp.data.repository.GetWeatherRepositoryImpl;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import com.javaweatherapp.domain.usecase.GetWeatherUseCase;
import com.javaweatherapp.domain.usecase.IGetWeatherUseCase;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
abstract class DomainModule {

  @Binds
  abstract IGetWeatherUseCase bindWeatherUseCase(GetWeatherUseCase weatherUseCase);
}
