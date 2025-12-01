package com.example.Exercise_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    var cityInput by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Helsinki")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Weather App",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = cityInput,
            onValueChange = { cityInput = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (cityInput.isNotBlank()) {
                    viewModel.fetchWeather(cityInput)
                    cityInput = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add City")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.weatherList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    when (item) {
                        is WeatherItem.Success -> {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = item.city, style = MaterialTheme.typography.titleMedium)
                                Text("Temperature: ${item.data.temp}°C")
                                Text("Humidity: ${item.data.humidity}%")
                            }
                        }
                        is WeatherItem.Error -> {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = item.city, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = item.message,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}