package jetpack.julian.ordenpapaapplication.Screen.Order.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetpack.julian.ordenpapaapplication.core.Utils
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem

@Composable
fun CardOrder(item: OrderPreparingRespondeItem, onClick: ((OrderPreparingRespondeItem) -> Unit)) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onClick(item)
            //socketManager.updateFood(item)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#${item.order_id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Mesa ${item.order_id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            val foods = item.order_foods

            for (food in foods) {
                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(food.food.name, fontSize = 12.sp )
                    var foodPrice = Utils.formatPrice(food.food.price.toDouble())
                    Text("$ $foodPrice", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.height(2.dp).fillMaxWidth().background(Color.Gray)
            ){}
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total ${item.order_foods.size}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Row {
                    Text(
                        text = "$",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                    Spacer(Modifier.width(7.dp))
                    val price = Utils.formatPrice(item.total_price.toDouble())
                    Text(
                        text = price,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
