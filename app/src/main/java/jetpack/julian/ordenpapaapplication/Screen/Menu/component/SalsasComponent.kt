package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxWidth
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
fun SalsasComponent() {
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
    val selectedSalsas = remember {
        mutableStateListOf(
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )
    }

    FlowRow (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        maxItemsInEachRow = 4,
        overflow = FlowRowOverflow.Clip
    ) {
        salsasFood.forEachIndexed { index, salsa ->
            FilterChip(
                onClick = { selectedSalsas[index] = !selectedSalsas[index] },
                label = { Text(salsa, color = Color.Black) },
                selected = selectedSalsas[index],
                leadingIcon = if (selectedSalsas[index]) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = if (selectedSalsas[index]) Color.Yellow else Color.Transparent,
                )
            )
        }
    }
}