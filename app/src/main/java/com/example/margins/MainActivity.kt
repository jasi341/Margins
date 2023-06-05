package com.example.margins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.margins.ui.theme.MarginsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarginsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Blue
                ) {
                    Greeting(name = "Android", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize().background(Color(0xFF00FF00))
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
           fontSize = getFontStyle(deviceType = DeviceType.MOBILE).headingFontSize,

        )
        Text(
            text = "Hello $name!",
            modifier = modifier,
            fontSize = getFontStyle(deviceType = DeviceType.TABLET).headingFontSize

        )
    }
}

@Preview(showBackground = true, device = Devices.WEAR_OS_SMALL_ROUND)
@Composable
fun GreetingPreview() {
    MarginsTheme {
        Greeting("Android")
    }
}