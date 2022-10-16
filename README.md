# Week-4-Assignment

<img src="https://raw.githubusercontent.com/Pazarama-Android-Kotlin-Bootcamp/week-4-assignment-MrNirva/main/WeatherApp/gif.gif" align="left" width="150" height="300"/>
<img src="https://raw.githubusercontent.com/Pazarama-Android-Kotlin-Bootcamp/week-4-assignment-MrNirva/main/WeatherApp/ss.png" height="300" width="750" >

## Summary
This weather application is coded in accordance with the single activity principle. Androidx and 3rd party libraries are used. Weather data obtained with Retrofit is saved to the memory with Data Store and then displayed when needed. This app has Light and Dark mode and can display Temperatures in Celsius, Fahrenheit and Kelvin. 
It works with the [OpenWeather API.](https://openweathermap.org/)

## Used in This Project

* [Navigation Component](https://developer.android.com/guide/navigation)
* [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
* [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Data Store](https://developer.android.com/jetpack/androidx/releases/datastore)
* [Shared Preferences](https://developer.android.com/reference/android/content/SharedPreferences)
* [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView)
* [CardView](https://developer.android.com/jetpack/androidx/releases/cardview)
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [Lottie Library](https://github.com/airbnb/lottie-android)
* [Gson](https://github.com/google/gson)

##
1. https://openweathermap.org/api kullanılarak bir hava durumu uygulaması geliştirilecektir. 2. https://marvelapp.com/prototype/70f2bha/screen/78478137 Buradan mockup ekranlarına bakabilirsiniz.
3. OpenWeatherMap’ten alınan API key’in girildiği ekran. Sonraki sayfada çağrılacak hava durumu web servisi bu API key değerini kullanacaktır.
4. Test için kullanılacak API Key: 8ddadecc7ae4f56fee73b2b405a63659
5. Hava durumu bilgisi: https://openweathermap.org/api/one-call-api
Hava durumu ikonları için: https://openweathermap.org/weather-conditions
6. Buradan sonraki isteklerde api Key’I interceptor kullanarak header’a göndermenizi istiyorum.
7. Giriş ekranları ve detay ekranlar tamamen fragment olmalı ve navigation component kullanılmalı.
8. APi’keyi local’de tutmak için sharedPref veya data store manager kullanabilirsiniz.
