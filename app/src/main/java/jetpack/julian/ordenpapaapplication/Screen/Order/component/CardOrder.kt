package jetpack.julian.ordenpapaapplication.Screen.Order.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetpack.julian.ordenpapaapplication.core.Utils
import jetpack.julian.ordenpapaapplication.core.Utils.toFormattedDate
import jetpack.julian.ordenpapaapplication.core.navigation.Menu
import jetpack.julian.ordenpapaapplication.core.patch
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import jetpack.julian.ordenpapaapplication.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardOrder(item: OrderPreparingRespondeItem, newClick: (OrderPreparingRespondeItem) -> Unit) {
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                showSheet = true
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
                    text = "Orden #${item.order_id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Mesa ${item.table}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(item.createdAt.toFormattedDate(), fontSize = 14.sp)
            val foods = item.order_foods
            foods.forEach { food ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = food.food.name,
                            fontSize = 14.sp,
                            lineHeight = 2.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = Utils.formatPrice(food.food.price.toDouble()),
                            lineHeight = 2.sp,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (food.extras.isNotEmpty()) {
                        Text(
                            text = food.extras.joinToString(", "),
                            lineHeight = 2.sp,
                            maxLines = 1,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (!food.notes.isNullOrEmpty()) {
                        Text(food.notes, lineHeight = 2.sp, fontSize = 12.sp)
                    }
                    Spacer(Modifier.height(5.dp))
                }

            }

            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total ${item.order_foods.size}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Row {
                    Text(
                        text = "$",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                    Spacer(Modifier.width(7.dp))
                    val price = Utils.formatPrice(item.total_price.toDouble())
                    Text(
                        text = price,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }



            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    containerColor = Color.White,
                    sheetState = sheetState
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Opciones de Orden",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "cerrar",
                                modifier = Modifier.clickable {
                                    showSheet = false
                                })
                        }
                        Spacer(Modifier.height(10.dp))


                        Button(
                            onClick = {
                                newClick(
                                    item
                                )
                                showSheet = false
                            },
                            colors = ButtonDefaults.buttonColors(Yellow),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Agregar Prodcuto")
                        }

                        Button(
                            onClick = {
                                Utils.socketManager.patchStatus(
                                    patch(orderId = item.order_id, status = "confirmed")
                                )
                                showSheet = false
                            },
                            colors = ButtonDefaults.buttonColors(Color.Green),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Finalizar Orden")
                        }

                        Button(
                            onClick = {
                                Utils.socketManager.patchStatus(
                                    patch(orderId = item.order_id, status = "canceled")
                                )
                                showSheet = false
                            },
                            colors = ButtonDefaults.buttonColors(Color.Red),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cancelar Orden")
                        }

                    }
                }
            }

        }
    }
}
