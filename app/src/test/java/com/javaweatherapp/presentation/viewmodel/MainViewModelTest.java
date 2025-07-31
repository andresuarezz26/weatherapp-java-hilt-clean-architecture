package com.javaweatherapp.presentation.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.javaweatherapp.domain.model.WeatherInfo;
import com.javaweatherapp.domain.usecase.GetWeatherUseCase;
import com.javaweatherapp.domain.usecase.IGetWeatherUseCase;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.*;
import org.junit.rules.TestRule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;

public class MainViewModelTest {

  @Rule
  public TestRule rule = new InstantTaskExecutorRule();

  @Mock
  private IGetWeatherUseCase getWeatherUseCase;

  @InjectMocks
  private MainViewModel viewModel;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxJavaPlugins.setSingleSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    viewModel = new MainViewModel(getWeatherUseCase);
  }

  @After
  public void tearDown() {
    RxJavaPlugins.reset();
  }

  @Test
  public void when_fetchWeatherInfo_then_liveDataUpdatedWithCorrectData() {
    WeatherInfo mockWeatherInfo = new WeatherInfo();
    when(getWeatherUseCase.execute(any(GetWeatherUseCase.Params.class)))
        .thenReturn(Single.just(mockWeatherInfo));

    viewModel.fetchWeatherInfo(3.2, 76, 7);

    assertEquals(mockWeatherInfo, viewModel.weatherInfo.getValue());
  }

  @Test
  public void when_fetchWeatherInfoFails_then_weatherInfoNotChanged() {
    // Given - set an initial value to weatherInfo
    WeatherInfo initialWeatherInfo = new WeatherInfo();
    viewModel.weatherInfo.setValue(initialWeatherInfo);

    when(getWeatherUseCase.execute(any(GetWeatherUseCase.Params.class)))
        .thenReturn(Single.error(new RuntimeException("Server error")));
    viewModel.fetchWeatherInfo(1, 2, 3);

    assertEquals(initialWeatherInfo, viewModel.weatherInfo.getValue());
  }

  @Test
  public void when_viewModelDisposed_thenCompositeDisposableIsDisposed() {
    viewModel.dispose();
    assertTrue(viewModel.composite.isDisposed());
  }
}
