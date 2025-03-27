package jetpack.julian.ordenpapaapplication.Screen.Order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import jetpack.julian.ordenpapaapplication.Screen.Order.component.CardOrder
import jetpack.julian.ordenpapaapplication.core.navigation.Menu
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import jetpack.julian.ordenpapaapplication.ui.theme.Yellow

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    orders: MutableState<List<OrderPreparingRespondeItem>>,
    navController: NavHostController
) {
    val context = LocalContext.current
    val foodsState = remember { mutableStateListOf<Food>() }
    val filteredOrders = orders.value
    val total_day = filteredOrders.filter {
        it.order_status != "canceled"
    }.sumOf { it.total_price }
    /*LaunchedEffect(Unit) {
        socketManager.setupListeners { foods ->
            foodsState.clear()
            foodsState.addAll(foods.filter { it.isprocess })
        }
    }*/

    Column(
        modifier = modifier
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Yellow),
            onClick = {
                //navController.navigate(OrderDetail(detail = null))
            }
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Total del día: ", color = Color.Black)
                Text("$${total_day}", color = Color.Black)
                /*Column {
                    Text(
                        text = "Lista de Ordenes",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Edita y agrega productos a pedidos del día",
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null, tint = Color.Black)
                }*/
            }
        }


        if (filteredOrders.isEmpty()) {

            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.Warning,
                    null,
                    modifier = Modifier
                        .size(50.dp)
                )
                Text(
                    text = "No hay órdenes en la cocina en este momento.",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )

            }

        } else {


            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(filteredOrders.sortedBy { order ->
                    when (order.order_status) {
                        "preparing" -> 1
                        "eating" -> 2
                        "confirmed" -> 3
                        "canceled" -> 4
                        else -> 5
                    }
                }, key = { item -> item.order_id }) { item ->
                    CardOrder(item) {
                        val res = Gson().toJson(it)
                        navController.navigate(Menu(table = it.table, orderId = it.order_id))
                    }
                }
            }
        }
    }

}