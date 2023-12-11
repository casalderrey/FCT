package com.example.gestiondemusica.utils.extension

fun <T> List<T>.getRandomItems(count: Int): List<T> {
    if(count >= size) { return toList() }

    val randomItems = mutableListOf<T>()
    val remainingItems = mutableListOf<Int>()

    while (randomItems.size < count) {
        val randomIndex = (indices).random()
        if(!remainingItems.contains(randomIndex)) {
            randomItems += this[randomIndex]
            remainingItems.add(randomIndex)
        }
    }

    return randomItems
}