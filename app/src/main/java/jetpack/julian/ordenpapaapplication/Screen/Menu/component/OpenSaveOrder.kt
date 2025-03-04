package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.Screen.Component.TotalPrice
import jetpack.julian.ordenpapaapplication.core.SelectFood


@Composable
fun OpenSaveOrder(listFoods: List<SelectFood>, table: Int, onDelete:(SelectFood) -> Unit , clickable: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(text = "Mesa $table", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("Lista de productos")
        Spacer(Modifier.height(7.dp))

        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listFoods) { item ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Card(
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            Modifier.padding(7.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFFFFE100))
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Text(text = item.food.name, fontWeight = FontWeight.Bold)
                                }
                                Text(
                                    text = "$${item.food.price.toInt()}",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(
                                Modifier.fillMaxWidth(1f)
                            ) {
                                Row(
                                    Modifier.fillMaxWidth(0.8f)
                                ) {
                                    Text(
                                        text = item.salsas.joinToString(", "),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Text(item.notes.orEmpty())
                            }
                        }
                    }


                    Button(
                        modifier = Modifier
                            .clip(RoundedCornerShape(2.dp))
                            .align(Alignment.BottomEnd),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = {
                            onDelete(item)
                        },
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Food")
                    }
                }
            }

        }
        var totalPrice = 0.0
        for (item in listFoods) {
            totalPrice += item.food.price
        }
        TotalPrice(listFoods.size, totalPrice.toInt())

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                if (listFoods.isNotEmpty()){
                    clickable()
                }
            }
        ) {
            Text("Enviar Order")
        }
    }
}
