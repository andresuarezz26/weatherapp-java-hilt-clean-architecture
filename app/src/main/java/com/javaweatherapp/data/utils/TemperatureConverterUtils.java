package com.javaweatherapp.data.utils;

public class TemperatureConverterUtils {
  /**
   * Converts a temperature from Kelvin to Celsius.
   * Formula: Celsius = Kelvin - 273.15
   *
   * @param kelvin The temperature in Kelvin.
   * @return The temperature in Celsius.
   */
  public static double kelvinToCelsius(double kelvin) {
    return kelvin - 273.15;
  }

  /**
   * Converts a temperature from Celsius to Fahrenheit.
   * Formula: Fahrenheit = (Celsius * 9/5) + 32
   *
   * @param celsius The temperature in Celsius.
   * @return The temperature in Fahrenheit.
   */
  public static double celsiusToFahrenheit(double celsius) {
    // Using 9.0 / 5.0 ensures floating-point division
    return (celsius * 9.0 / 5.0) + 32.0;
  }

  /**
   * Converts a temperature from Kelvin directly to Fahrenheit.
   * This method first converts Kelvin to Celsius, then Celsius to Fahrenheit.
   * It serves as a convenient utility method for direct conversion.
   *
   * @param kelvin The temperature in Kelvin.
   * @return The temperature in Fahrenheit.
   */
  public static double kelvinToFahrenheit(double kelvin) {
    double celsius = kelvinToCelsius(kelvin);
    return celsiusToFahrenheit(celsius);
  }
}
