package com.example.gestiondemusica.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.documentfile.provider.DocumentFile
import java.io.File

object FilesUtils {

    val RUTA_DISPOSITIVO = Environment.getExternalStorageDirectory()
    //val RUTA_APP = ctxt.getExternalFilesDir(null)

    fun getDirectory(context: Context, folder: String = "/primary%3Abluetooth"): DocumentFile? {
        //val treeUri = Uri.parse("content://com.android.providers.downloads.documents/tree/downloads")
        val treeUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3Abluetooth")
        val docUri = Uri.withAppendedPath(treeUri, folder)
        val documentFile = DocumentFile.fromTreeUri(context, docUri)
        return documentFile
    }



    /*TODO: Organizar*/

    fun pedirPermiso(contexto: Context, actividad: Activity) {
        if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permiso
            ActivityCompat.requestPermissions(actividad, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            //SongsVM.comprobarListar()
        }
    }

    fun comprobarDirectorio(ruta: String): Boolean {

        println("Comprobando la ruta: $ruta")
        var dir = File("${ruta}")

        if(dir.exists()) {
            println("La ruta existe.")
            if(dir.isDirectory) {
                println("La ruta: es directorio.")
                if(dir.canRead()){
                    println("La ruta se puede leer.")
                    return true
                }else{
                    println("Error: La ruta no se puede leer.")
                }
            }else {
                println("Error: La ruta no es directorio.")
            }
        }else {
            println("Error: La ruta no existe.")
        }
        return false
    }

/*
    fun getDirectory(context: Context, folder: String): DocumentFile? {
        val rutaDispositivo = Environment.getExternalStorageDirectory().toString() + "/Download"
        val file = File(rutaDispositivo)
        val uri = Uri.fromFile(file)

        val documentFile = DocumentFile.fromSingleUri(context, uri)
        return documentFile
    }
*/

}