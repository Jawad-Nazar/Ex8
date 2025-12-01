# Ex8
# Weather App (Jetpack Compose + Retrofit)

A simple Android app built with Kotlin, Jetpack Compose, and Retrofit that fetches real-time weather data (temperature & humidity) from the OpenWeatherMap API and displays multiple cities in a scrollable list.

# Features

Fetch weather data (temperature & humidity) for any city

Uses Retrofit + Gson converter

Displays results in a LazyColumn

Supports stacking multiple cities’ weather info

Clean Compose UI with ViewModel state management

# API

Uses the free OpenWeatherMap API:

BASE_URL = "https://api.openweathermap.org/data/2.5/"


Example test URL:

https://api.openweathermap.org/data/2.5/weather?q=Helsinki&units=metric&appid=<your_api_key>


# Retrofit endpoint parameters:

@Query("q") city: String,
@Query("appid") apiKey: String,
@Query("units") units: String = "metric"

# Permissions

Add to AndroidManifest.xml:

<uses-permission android:name="android.permission.INTERNET" />

# Dependencies

Add to build.gradle.kts (Module: app):

implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
