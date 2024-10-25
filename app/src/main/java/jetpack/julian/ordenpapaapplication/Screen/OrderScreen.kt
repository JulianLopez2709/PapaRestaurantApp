package jetpack.julian.ordenpapaapplication.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import jetpack.julian.ordenpapaapplication.Utils
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.navigation.NewFood
import jetpack.julian.ordenpapaapplication.model.food.Food

@Composable
fun OrderScreen(modifier: Modifier = Modifier , orders: MutableState<List<Food>>) {
    val foodsState = remember { mutableStateListOf<Food>() }

    LaunchedEffect(Unit) {
        socketManager.setupListeners { foods ->
            foodsState.clear()
            foodsState.addAll(foods.filter { it.isprocess })
        }
    }

    Column{
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(orders.value.filter { it.isprocess }) { item ->
                CardFood(item) {}
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}