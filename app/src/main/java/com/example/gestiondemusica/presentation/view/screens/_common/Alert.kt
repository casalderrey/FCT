package com.example.gestiondemusica.presentation.view.screens._common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/*
@Composable
fun Alert(
    title: String,
    onShow: (Boolean) -> Unit,
    onConfirm: (String) -> Unit,
    content: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = { onShow(false) },
        confirmButton = {
            TextButton(onClick = { onShow(false); onConfirm("") }) {
                Text(text = "Añadir")
            }
        },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = { onShow(false) }) {
                Text(text = "Cancelar")
            }
        },
        text = { content() },
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
    )
}
*/

@Composable
fun Alert(onShow:(Boolean)->Unit, onAdd:(String)->Unit) {
    var nameList by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onShow(false) },
        confirmButton = {
            TextButton(onClick = { onShow(false); onAdd(nameList) }) {
                Text(text = "Añadir")
            }
        },
        dismissButton = {
            TextButton(onClick = { onShow(false) }) {
                Text(text = "Cancelar")
            }
        },
        text = {
            OutlinedTextField(
                value = nameList,
                onValueChange = { nameList = it },
                label = { Text(text = "Lista") },
                placeholder = { Text(text = "nombre de la lista") }
            )
        },
        title = { Text(text = "Crear una nueva lista") }

    )
}

@Composable
fun listofLists(
    items: List<String>,
    titleText: String,
    onConfirm: (List<String>) -> Unit, // (1)
    onShow:(Boolean)->Unit
) {
    val (selectedOptions, selectOptions) = remember { mutableStateOf(emptyList<String>()) }

    AlertDialog(
        onDismissRequest = { onShow(false) },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedOptions); onShow(false) }) {
                Text(text = "Añadir")
            }
        },
        dismissButton = {
            TextButton(onClick = { onShow(false) }) {
                Text(text = "Cancelar")
            }
        },
        text = {
            Column {
                Divider()

                items.filter { it != "Todas" && it != "Género" && it != "Artista" }
                    .let {
                        if(it.isNotEmpty()) {
                            it.forEach { currentItem ->
                                val isChecked = currentItem in selectedOptions// (1)
                                ItemRow(
                                    item = currentItem,
                                    checked = isChecked,
                                    onValueChange = { checked ->
                                        val checkedItems = selectedOptions.toMutableList()

                                        if (checked)
                                            checkedItems.add(currentItem) // (2)
                                        else
                                            checkedItems.remove(currentItem) // (3)

                                        selectOptions(checkedItems) // (4)
                                    }
                                )
                            }
                        }else {
                            Text(text = "No hay listas.")
                        }
                    }

                Divider()
            }
        },
        title = { Text(text = titleText, style = MaterialTheme.typography.labelMedium) }

    )
}

@Composable
private fun ItemRow(
    item: String,
    checked: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .toggleable(
                value = checked,
                onValueChange = onValueChange,
                role = Role.Checkbox
            )
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = null)

        Spacer(modifier = Modifier.width(32.dp))

        Text(text = item)
    }
}