package com.javaweatherapp.presentation.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.javaweatherapp.domain.usecase.GetWeatherUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import javax.inject.Inject;

@HiltViewModel
public class MainViewModel extends ViewModel {

  private final GetWeatherUseCase getWeatherUseCase;
  private final CompositeDisposable composite = new CompositeDisposable();

  @Inject
  public MainViewModel(GetWeatherUseCase getWeatherUseCase) {
    this.getWeatherUseCase = getWeatherUseCase;
    // Default constructor for Hilt
  }

  public void executeUseCase() {
    composite.add(
        getWeatherUseCase.execute(new GetWeatherUseCase.Params(3.2,76, 7))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            response -> Log.d("TAG", "Weather response: " + response),
            throwable -> Log.e("TAG", "Error getting weather", throwable)
        ));
  }

  public void dispose() {
    composite.dispose();
  }
}
