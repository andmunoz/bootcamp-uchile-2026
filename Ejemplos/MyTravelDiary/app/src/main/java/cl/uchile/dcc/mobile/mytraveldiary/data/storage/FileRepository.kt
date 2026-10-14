package cl.uchile.dcc.mobile.mytraveldiary.data.storage

import android.util.Log
import java.io.File

class FileRepository(
    private val baseDir: File
) {
    private val appDir: File
        get() = File(baseDir, "my_travel_files").apply {
            if (!exists()) mkdirs()
        }

    fun saveFile(tempFile: File): String? {
        return try {
            val target = File(appDir, tempFile.name)
            if (target.exists())
                target.delete()
            tempFile.copyTo(target)
            target.absolutePath
        } catch (e: Exception) {
            Log.e("FileRepository", "Error al guardar el archivo", e)
            null
        }
    }

    fun loadFile(fileName: String): File? {
        val file = File(appDir, fileName)
        return if (file.exists()) file else null
    }
}