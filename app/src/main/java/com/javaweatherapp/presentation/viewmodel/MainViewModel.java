package com.javaweatherapp.presentation.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.javaweatherapp.domain.model.WeatherInfo;
import com.javaweatherapp.domain.usecase.GetWeatherUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import javax.inject.Inject;

@HiltViewModel
public class MainViewModel extends ViewModel {

  private final GetWeatherUseCase getWeatherUseCase;
  private final CompositeDisposable composite = new CompositeDisposable();

  public final MutableLiveData<WeatherInfo> weatherInfo = new MutableLiveData<>();
  @Inject
  public MainViewModel(GetWeatherUseCase getWeatherUseCase) {
    this.getWeatherUseCase = getWeatherUseCase;
  }

  public void fetchWeatherInfo(double lat, double lon, int cnt) {
    composite.add(getWeatherUseCase.execute(new GetWeatherUseCase.Params(lat, lon, cnt))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(weatherInfo::setValue,
            throwable -> Log.e("TAG", "Error getting weather", throwable)));
  }

  public void dispose() {
    composite.dispose();
  }
}
