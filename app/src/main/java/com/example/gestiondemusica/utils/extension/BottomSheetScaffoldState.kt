package com.example.gestiondemusica.utils.extension

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.ExperimentalMaterialApi

/**
 * Align fraction states into single value
 *
 *  1.0f - Expanded
 *  0.0f - Collapsed
 */
@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentFraction: Float
    get() {
        val fraction = bottomSheetState.progress/*.fraction*/
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed && fraction == 1f -> 0f
            currentValue == Collapsed && targetValue == Collapsed -> fraction
            currentValue == Expanded && targetValue == Expanded && fraction == 0f -> 1f
            currentValue == Expanded && targetValue == Expanded && fraction == 1f -> 1f
            currentValue == Expanded && targetValue == Expanded && fraction != 0f -> 1f - fraction
            currentValue == Collapsed && targetValue == Expanded -> fraction
            else -> 1f - fraction
        }
    }