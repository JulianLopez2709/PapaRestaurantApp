package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SalsasComponent(type : String,onSelectionChanged: (List<String>) -> Unit) {
    val salsasFood = listOf(
        "Chipote",
        "Leña",
        "Prohida",
        "Dulce de Maiz",
        "Mostaneza",
        "Salsa Rosada",
        "Showy Ajo",
        "Piña",
        "BBQ",
        "Queso Cheddar",
        "Tomate"
    )

    val salsasIceCream = listOf(
        "Mora",
        "Chocolate",
        "leche condensada",
    )


    val selectedSalsas = remember { mutableStateListOf<String>() }


    FlowRow (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        overflow = FlowRowOverflow.Clip
    ) {
        val salsas = if (type == "food") salsasFood else salsasIceCream
        salsas.forEach { salsa ->
            val isSelected = selectedSalsas.contains(salsa)

            FilterChip(
                onClick = {
                    if (selectedSalsas.contains(salsa)) {
                        selectedSalsas.remove(salsa)
                    } else {
                        selectedSalsas.add(salsa)
                    }
                    onSelectionChanged(selectedSalsas.toList())
                },
                label = { Text(salsa) },
                selected = selectedSalsas.contains(salsa),
                modifier = Modifier.padding(bottom = 0.dp)
            )
        }
    }
}