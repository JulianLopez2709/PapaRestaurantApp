package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import jetpack.julian.ordenpapaapplication.Screen.Component.TotalPrice
import jetpack.julian.ordenpapaapplication.core.SelectFood
import jetpack.julian.ordenpapaapplication.ui.theme.Gray
import jetpack.julian.ordenpapaapplication.ui.theme.Yellow


@Composable
fun OpenSaveOrder(
    listFoods: List<SelectFood>,
    table: Int,
    onDelete: (SelectFood) -> Unit,
    clickable: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Lista de productos",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Text(text = "Mesa $table", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(Modifier.height(7.dp))

        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(listFoods) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        shape = RectangleShape,
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = {
                            onDelete(item)
                        },
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Food")
                    }

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.food.name, fontWeight = FontWeight.Bold)
                            Text(
                                text = "$${item.food.price.toInt()}",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(
                            Modifier.fillMaxWidth(1f)
                        ) {
                            if (item.salsas.isNotEmpty()) {
                                Text(
                                    text = item.salsas.joinToString(", "),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            if (!item.notes.isNullOrEmpty()) {
                                Text(item.notes)
                            }
                        }
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
                .padding(5.dp)
                .clip(RoundedCornerShape(15.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Yellow),
            onClick = {
                if (listFoods.isNotEmpty()) {
                    clickable()
                }
            }
        ) {
            Text("Enviar Order")
        }
    }
}
