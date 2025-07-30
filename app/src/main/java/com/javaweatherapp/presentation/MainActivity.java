package com.javaweatherapp.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.javaweatherapp.R;
import dagger.hilt.android.AndroidEntryPoint;
import androidx.lifecycle.ViewModelProvider;
import com.javaweatherapp.presentation.viewmodel.MainViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private MainViewModel mainViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
  }
}
