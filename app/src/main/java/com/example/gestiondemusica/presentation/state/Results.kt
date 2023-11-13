package com.example.gestiondemusica.presentation.state

import java.lang.Exception

sealed class Results<out T> {
    data class success<out T>(val data: T): Results<T>()
    data class error(val exception: Exception): Results<Nothing>()
}