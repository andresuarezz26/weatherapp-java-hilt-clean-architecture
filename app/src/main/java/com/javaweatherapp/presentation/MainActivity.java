package com.javaweatherapp.presentation;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.javaweatherapp.R;
import dagger.hilt.android.AndroidEntryPoint;
import androidx.lifecycle.ViewModelProvider;
import com.javaweatherapp.presentation.viewmodel.MainViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.javaweatherapp.presentation.adapter.WeatherAdapter;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private MainViewModel mainViewModel;
  private WeatherAdapter weatherAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    TextView cityNameTextView = findViewById(R.id.textCityName);
    TextView countryNameTextView = findViewById(R.id.textCountryName);

    weatherAdapter = new WeatherAdapter();
    recyclerView.setAdapter(weatherAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    mainViewModel.weatherInfo.observe(this,
        weatherInfo -> {
          final String cityText = weatherInfo.getCityName().isEmpty() ? "City: N/A" : "City: " + weatherInfo.getCityName();
          final String countryText = weatherInfo.getCountry().isEmpty() ? "Country: N/A" : "Country: " + weatherInfo.getCountry();
          cityNameTextView.setText(cityText);
          countryNameTextView.setText(countryText);
      weatherAdapter.setItems(weatherInfo.getWeatherInfoList());

    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    mainViewModel.fetchWeatherInfo();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mainViewModel.dispose();
  }
}
