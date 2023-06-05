package com.example.margins

import android.content.res.Configuration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class DeviceType{
    MOBILE,
    TABLET
}

data class DeviceDimensions(
    val start: Dp,
    val end: Dp,
    val top: Dp = 0.dp,
    val bottom: Dp = 0.dp,

)

fun getDeviceDimensions(
    deviceType: DeviceType,
    screenOrientation: Configuration
): DeviceDimensions {
    return when (deviceType) {
        DeviceType.MOBILE -> {
            if (screenOrientation.orientation == Configuration.ORIENTATION_PORTRAIT) {
                DeviceDimensions(
                    start = 15.dp,
                    end = 15.dp,
                    top = 20.dp,
                    bottom = 10.dp,


                )
            } else {
                DeviceDimensions(
                    start = 15.dp,
                    end = 15.dp,
                    top = 20.dp,
                    bottom = 10.dp,

                )
            }
        }

        DeviceType.TABLET -> {
            if (screenOrientation.orientation == Configuration.ORIENTATION_PORTRAIT) {
                DeviceDimensions(
                    start = 90.dp,
                    end = 90.dp,
                    top = 72.dp,
                    bottom = 50.dp,
                )
            } else {
                DeviceDimensions(
                    start = 66.dp,
                    end = 66.dp,
                    bottom = 200.dp,
                    top = 200.dp,
                )
            }
        }
    }
}

fun getFontStyle(deviceType: DeviceType):TextStyle{
    return when(deviceType){
        DeviceType.MOBILE -> {
            TextStyle(
                fontSize = 16.sp,
            )
        }
        DeviceType.TABLET -> {
            TextStyle(
                fontSize = 28.sp,
            )
        }
    }
}
