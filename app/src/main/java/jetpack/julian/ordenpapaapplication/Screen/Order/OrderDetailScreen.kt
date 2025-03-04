package jetpack.julian.ordenpapaapplication.Screen.Order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.Screen.Component.TotalPrice
import jetpack.julian.ordenpapaapplication.core.Utils
import jetpack.julian.ordenpapaapplication.core.Utils.formatPrice
import jetpack.julian.ordenpapaapplication.core.Utils.toFormattedDate
import jetpack.julian.ordenpapaapplication.core.navigation.Home
import jetpack.julian.ordenpapaapplication.core.navigation.Menu
import jetpack.julian.ordenpapaapplication.core.patch
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import jetpack.julian.ordenpapaapplication.ui.theme.Yellow

@Composable
fun OrderDetailScreen(order: OrderPreparingRespondeItem, navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fries),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.White),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
            ) {
                Text(
                    "Mesa ${order.table}",
                    modifier = Modifier.padding(7.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(
                    Color.White
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(

                    ) {
                        Text(
                            "Orden #${order.order_id}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(order.createdAt.toFormattedDate())
                    }
                    Button(
                        onClick = {
                            navController.navigate(
                                Menu(
                                    table = order.table,
                                    orderId = order.order_id
                                )
                            )
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(Yellow),
                        shape = CircleShape
                    ) {
                        Icon(
                            Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                }

                Spacer(Modifier.height(5.dp))
                //
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    items(order.order_foods) { item ->
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
                                        text = "$${formatPrice(item.food.price.toDouble())}",
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
                                            text = item.extras.joinToString(", "),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                    Text(item.notes.orEmpty())
                                }
                            }
                        }
                    }
                }
                TotalPrice(order.order_foods.size, order.total_price)
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier,
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            //order cancelada
                            Utils.socketManager.patchStatus(
                                patch(
                                    orderId = order.order_id,
                                    status = "canceled"
                                )
                            )
                            navController.navigate(Home)
                        },
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("x")
                    }
                    Spacer(Modifier.width(5.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            //order completed
                            Utils.socketManager.patchStatus(
                                patch(
                                    orderId = order.order_id,
                                    status = "confirmed"
                                )
                            )
                            navController.navigate(Home)
                        },
                        colors = ButtonDefaults.buttonColors(Color.Green),
                    ) {
                        Text("Order Finalizada Correctamente")
                    }
                }
            }
        }
    }

}