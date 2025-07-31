# Weather App 

Weather App is an Android application developed using Clean Architecture, MVVM, Java, Hilt, and RxJava3. It retrieves weather data from https://openweathermap.org/ and presents it in a simplified UI, while also providing offline capabilities.

## Demo
<img width="281" height="619" alt="Screenshot 2025-07-31 at 9 42 32 AM" src="https://github.com/user-attachments/assets/b3c4e13f-53f5-40e9-b647-56a107238c47" />

## How run the app
- Clone the repository writing on a terminal this command:
  ```git clone git@github.com:andresuarezz26/weatherapp-java-hilt-clean-architecture.git```
- Once the repository is cloned on your local, open it with the latest Android Studio version (Android Studio Narwhal | 2025.1.1 when this was written)
- Press button Sync Projects with Gradle Files (the one who looks like an elephant with an arrow)
- Press button Build > Assemble 'app' Run Configuration
- Press button Run 'app'

## Architecture

The architecture of the app is an implementation of Clean Architecture as defined by Robert C. Martin: https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164
- Presentation: This layer is in charge of interacting with the Android system for UI-related tasks.
- Domain: This is where the use cases live. It is a pure Java layer containing the application's business logic. 
- Data: This layer is in charge of interacting with data. It fetches data from the server, saves information in a local database, and orchestrates the logic to manage different data sources.
  

## Design patterns used 
These are some examples of design patterns used in the app:

### Behavioral

Observer/Listener: used across the whole app with the help of RxJava to communicate between different components.


### Creational


Factory:  

The view model is constructed using the `ViewModelProvider`, which is a factory. Receiving the view model's class name then constructs the instance.
`mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);`

### Structural Patterns

Adapter: 

`WeatherAdapter` uses the Adapter design pattern to transform `WeatherInfoItem` into view items for display within the RecyclerView.

Facade:

```
public interface GetWeatherRepository {

  Single<WeatherInfo> invoke(double lat, double lon, int cnt);

}
```

The `GetWeatherRepository` interface use a facade pattern to hide the complexities of the `GetWeatherRepositoryImpl`.

Composite:

Inside the `MainViewModel`, we use a `CompositeDisposable` to compose zero or more similar objects, allowing them to be manipulated as a single object.


