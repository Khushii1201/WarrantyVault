package com.example.warrantyvault.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object FileUtils {
    fun copyUriToInternalStorage(context: Context, uri: Uri): String {
        val directory = File(context.filesDir, "invoices")
        if (!directory.exists()) directory.mkdirs()

        val file = File(directory, "invoice_${System.currentTimeMillis()}.jpg")

        context.contentResolver.openInputStream(uri).use { input ->
            FileOutputStream(file).use { output ->
                requireNotNull(input) { "Unable to open selected file." }
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }
}
