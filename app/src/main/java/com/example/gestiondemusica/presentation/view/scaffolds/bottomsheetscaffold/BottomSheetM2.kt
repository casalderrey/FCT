package com.example.gestiondemusica.presentation.view.scaffolds.bottomsheetscaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.utils.extension.currentFraction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetM2(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    paddingValues: PaddingValues,
    content: @Composable (PaddingValues) -> Unit,
) {

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    val sheetToggle: () -> Unit = {
        scope.launch {
            if (scaffoldState.bottomSheetState.isCollapsed) {
                scaffoldState.bottomSheetState.expand()
            } else {
                scaffoldState.bottomSheetState.collapse()
            }
        }
    }

//    val radius = (30 -(30 * scaffoldState.currentFraction)).dp

//    val fraction = scaffoldState.bottomSheetState.progress
//    val targetValue = scaffoldState.bottomSheetState.targetValue
//    val currentValue = scaffoldState.bottomSheetState.currentValue
//    val newFraction = scaffoldState.currentFraction

//    Log.d("DebugTag", "Debug on: Compose.BottomSheetM2,\n  " +
//            "Fraction = $fraction,\n  " +
//            "target = $targetValue,\n  " +
//            "current = $currentValue,\n  " +
//            "New fraction = $newFraction,\n  "
//    )

    val screenHeightDp = LocalContext.current.resources.displayMetrics.heightPixels.dp
    val minHeight = 70.dp

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        //sheetShape = RoundedCornerShape(radius),
        sheetContent = {
            SheetContentConf(1f) {
                SheetContentBoth(
                    isCollapsed = scaffoldState.bottomSheetState.isCollapsed,
                    fractionSheet = scaffoldState.currentFraction,
                    screenHeightDp = screenHeightDp,
                    minHeightSheet = minHeight,
                    onSheetClick = sheetToggle,
                    reproVM = reproVM,
                    userVM = userVM,
                    modifier = Modifier,
                )
            }

        },
        sheetPeekHeight = if(!reproVM.song?.songUrl.isNullOrBlank()){ minHeight }else{ 0.dp },
    ) {
        content(it)
    }

}
