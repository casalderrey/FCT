package com.example.gestiondemusica.utils

fun formatDuration(duration: Long): String {
    val seconds = duration / 1000
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}

fun <T> getRandomItemsFromList(list: List<T>, numOfItems: Int): List<T> {
    val randomItems = mutableListOf<T>()

    while (randomItems.size < numOfItems) {
        val randomIndex = (list.indices).random()
        randomItems += list[randomIndex]
    }

    return randomItems
}