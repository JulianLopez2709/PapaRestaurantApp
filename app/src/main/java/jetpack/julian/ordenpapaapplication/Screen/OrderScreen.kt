package jetpack.julian.ordenpapaapplication.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food

@Composable
fun OrderScreen(modifier: Modifier = Modifier, orders: MutableState<List<Food>>) {
    val foodsState = remember { mutableStateListOf<Food>() }

    LaunchedEffect(Unit) {
        socketManager.setupListeners { foods ->
            foodsState.clear()
            foodsState.addAll(foods.filter { it.isprocess })
        }
    }

    Column {
        val filteredOrders = orders.value.filter { it.isprocess }

        if (filteredOrders.isEmpty()) {

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Yellow)
            )
            Text(
                text = "No hay pedidos en proceso.",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(16.dp),
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )
            Icon(
                Icons.Outlined.Warning,
                null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Yellow)
            )

        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(filteredOrders) { item ->
                    CardFood(item) {}
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

    }

}