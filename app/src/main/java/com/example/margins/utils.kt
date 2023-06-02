package com.example.margins

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors


enum class DeviceType{
    MOBILE,
    TABLET
}

data class DeviceDimensions(
    val start: Dp,
    val end: Dp,
    val top: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val normalTextStyle: TextStyle,
    val boldTextStyle : TextStyle
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

                    normalTextStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.Cursive
                    ),

                    boldTextStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.Cursive
                    ),
                )
            } else {
                DeviceDimensions(
                    start = 15.dp,
                    end = 15.dp,
                    top = 20.dp,
                    bottom = 10.dp,
                    normalTextStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.SansSerif
                    ),

                    boldTextStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 32.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.Cursive
                    ),
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
                    normalTextStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.Cursive
                    ),

                    boldTextStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                )
            } else {
                DeviceDimensions(
                    start = 66.dp,
                    end = 66.dp,
                    bottom = 200.dp,
                    top = 200.dp,
                    normalTextStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.Cursive
                    ),

                    boldTextStyle = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontFamily = FontFamily.SansSerif
                    ),
                )
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun PreviewViewComposable() {
    AndroidView({ context ->
        val cameraExecutor = Executors.newSingleThreadExecutor()
        val previewView = PreviewView(context).also {
            it.scaleType = PreviewView.ScaleType.FILL_CENTER
            it.background = ContextCompat.getDrawable(context, R.drawable.back)
            it.implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val imageCapture = ImageCapture.Builder().build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, BarcodeAnalyser{
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    })
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    context as ComponentActivity, cameraSelector, preview, imageCapture, imageAnalyzer)

            } catch(exc: Exception) {
                Log.e("DEBUG", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(context))
        previewView
    },
        modifier = Modifier
            .size(width = 250.dp, height = 250.dp))
}