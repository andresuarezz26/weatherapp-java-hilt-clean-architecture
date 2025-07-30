package com.javaweatherapp.data.di;

import com.javaweatherapp.data.service.ApiService;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

  @Provides
  @Singleton
  public OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return new OkHttpClient.Builder()
        .cache(null)
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @Provides
  @Singleton
  public Converter.Factory providesConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  public Retrofit providesRetrofit(
      OkHttpClient okHttpClient,
      Converter.Factory converterFactory
  ) {
    return new Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // <-- ADD THIS!
        .build();
  }

  @Provides
  @Singleton
  public com.javaweatherapp.data.service.ApiService providesApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }
}
