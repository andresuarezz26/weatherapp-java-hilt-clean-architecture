package com.javaweatherapp.presentation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.javaweatherapp.R;
import dagger.hilt.android.AndroidEntryPoint;
import androidx.lifecycle.ViewModelProvider;
import com.javaweatherapp.presentation.viewmodel.MainViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.javaweatherapp.presentation.adapter.WeatherAdapter;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private MainViewModel mainViewModel;
  private WeatherAdapter weatherAdapter;
  private FusedLocationProviderClient fusedLocationClient;
  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

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

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    // Check permission and fetch location
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this,
          new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
          LOCATION_PERMISSION_REQUEST_CODE);
      return;
    }
    getLastLocationAndFetchWeather();
  }

  private void getLastLocationAndFetchWeather() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      return;
    }
    fusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, location -> {
          if (location != null) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            mainViewModel.fetchWeatherInfo(lat, lon, 7);
          }
        });
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == LOCATION_PERMISSION_REQUEST_CODE
        && grantResults.length > 0
        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      getLastLocationAndFetchWeather();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mainViewModel.dispose();
  }
}
