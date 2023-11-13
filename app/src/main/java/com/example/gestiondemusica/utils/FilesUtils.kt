package com.example.gestiondemusica.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

/*
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    <application
        android:requestLegacyExternalStorage="true"
        >
    </application>
</manifest>
*/

object FilesUtils {
    val RUTA_DISPOSITIVO = Environment.getExternalStorageDirectory()
    //val RUTA_APP = ctxt.getExternalFilesDir(null)

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

}