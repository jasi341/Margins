package com.example.margins

import android.content.res.Configuration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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

data class FontsDimensions(
    val bodyFontSize:TextUnit,
    val headingFontSize:TextUnit,
    val buttonFontSize :TextUnit,
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
            }
            else {
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
            }
            else {
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

fun getFontStyle(
    deviceType: DeviceType,
)
        :FontsDimensions {
    return when (deviceType) {
        DeviceType.MOBILE -> {
            FontsDimensions(
                headingFontSize = 20.sp,
                bodyFontSize = 14.sp,
                buttonFontSize = 14.sp,
            )
        }

        DeviceType.TABLET -> {
            FontsDimensions(
                headingFontSize = 22.sp,
                bodyFontSize = 14.sp,
                buttonFontSize = 14.sp,
            )

        }
    }
}

