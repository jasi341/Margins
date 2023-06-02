package com.example.margins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.size(250.dp)
                    ) {
                        PreviewViewComposable()
                        Text(
                            text = "Scan QR Code",
                            modifier = Modifier.padding(top = 48.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            style = getDeviceDimensions(
                deviceType = DeviceType.MOBILE,
                screenOrientation = LocalConfiguration.current
            ).normalTextStyle
        )
        Text(
            text = "Hello $name!",
            modifier = modifier,
            style = getDeviceDimensions(
                deviceType = DeviceType.MOBILE,
                screenOrientation = LocalConfiguration.current
            ).boldTextStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarginsTheme {
        Greeting("Android")
    }
}