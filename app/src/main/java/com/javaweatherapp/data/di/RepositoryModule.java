package com.javaweatherapp.data.di;

import com.javaweatherapp.data.repository.GetWeatherRepositoryImpl;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
abstract class RepositoryModule {

  @Binds
  abstract GetWeatherRepository bindWeatherRepository(GetWeatherRepositoryImpl weatherRepositoryImpl);
}
