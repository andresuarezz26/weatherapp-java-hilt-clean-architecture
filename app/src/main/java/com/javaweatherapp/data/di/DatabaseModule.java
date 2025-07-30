package com.javaweatherapp.data.di;

import android.content.Context;
import androidx.room.Room;
import com.javaweatherapp.data.local.AppDatabase;
import com.javaweatherapp.data.local.dao.GetWeatherDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

  @Singleton
  @Provides
  public AppDatabase provideDatabase(@ApplicationContext Context context) {
    return Room.databaseBuilder(context, AppDatabase.class, "weather-database")
        .fallbackToDestructiveMigration()
        .build();
  }

  @Singleton
  @Provides
  public GetWeatherDao provideGetWeatherDao(AppDatabase database) {
    return database.getWeatherDao();
  }
}
