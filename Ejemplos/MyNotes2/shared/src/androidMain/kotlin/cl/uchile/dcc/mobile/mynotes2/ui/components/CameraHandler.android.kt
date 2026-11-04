package cl.uchile.dcc.mobile.mynotes2.ui.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class CameraHandler(
    private val context: Context,
    private val cameraLauncher: ActivityResultLauncher<Void?>,
    private val permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    private var onSuccessCallback: ((String) -> Unit)? = null
    private var onErrorCallback: ((String) -> Unit)? = null

    fun onResult(bitmap: Bitmap?) {
        if (bitmap != null) {
            val savedPicture = saveBitmapToInternalStorage(bitmap)
            onSuccessCallback?.invoke(savedPicture.absolutePath)
        } else {
            onErrorCallback?.invoke("Captura cancelada")
        }
    }

    private fun saveBitmapToInternalStorage(bitmap: Bitmap): File {
        val file = File(
            context.filesDir,
            "note_picture_${System.currentTimeMillis()}.jpg"
        )
        FileOutputStream(file).use { out ->
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                90,
                out
            )
        }
        return file
    }

    actual fun takePicture(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        this.onSuccessCallback = onSuccess
        this.onErrorCallback = onError

        // 1. Verificamos permiso de cámara
        val hasPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            cameraLauncher.launch(null)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

@Composable
actual fun rememberCameraHandler(): CameraHandler {
    val context = LocalContext.current
    var cameraHandler by remember { mutableStateOf<CameraHandler?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        cameraHandler?.onResult(bitmap)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(null)
        }
    }

    val handler = remember {
        CameraHandler(context, cameraLauncher, permissionLauncher).also {
            cameraHandler = it
        }
    }
    return handler
}