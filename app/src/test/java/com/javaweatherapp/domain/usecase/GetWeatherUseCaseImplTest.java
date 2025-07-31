package com.javaweatherapp.domain.usecase;

import com.javaweatherapp.domain.model.WeatherInfo;
import com.javaweatherapp.domain.repository.GetWeatherRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GetWeatherUseCaseImplTest {

  @Mock
  private GetWeatherRepository repository;

  private GetWeatherUseCaseImpl useCase;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxJavaPlugins.setSingleSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    useCase = new GetWeatherUseCaseImpl(repository);
  }

  @After
  public void tearDown() {
    RxJavaPlugins.reset();
  }

  @Test
  public void whenExecuteUseCase_withParams_thenReturnsWeatherInfo() {
    WeatherInfo mockWeatherInfo = new WeatherInfo();
    when(repository.invoke(anyDouble(), anyDouble(), anyInt())).thenReturn(
        Single.just(mockWeatherInfo));

    GetWeatherUseCaseImpl.Params params = new GetWeatherUseCaseImpl.Params(1.23, 4.56, 7);
    WeatherInfo result = useCase.execute(params).blockingGet();

    // Verify repository was called with correct arguments
    verify(repository, times(1)).invoke(1.23, 4.56, 7);

    // Assert the result
    assertEquals(mockWeatherInfo, result);
  }
}