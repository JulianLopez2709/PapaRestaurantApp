package jetpack.julian.ordenpapaapplication.Screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.util.Locale


@Composable
fun NewScreen(item: Food, navController: NavHostController) {

    var price by remember {
        mutableIntStateOf(item.price)
    }

    var quantity by remember { mutableStateOf(1) }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("1") }
    val options = listOf("1", "2", "3", "4", "5")


    val salsas = listOf("Mayonesa", "Ketchup", "Mostaza", "BBQ")
    val selectedSalsas = remember { mutableStateListOf(false, false, false, false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.size(48.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = "Nuevo Pedido",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(Color.White)

        ) {
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth()
                    .padding(25.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, text = item.title)
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        text = "${item.duration}min"
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))


                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Yellow),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Descripción: ${item.description}")
                }

                Spacer(modifier = Modifier.width(8.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Mesa ")
                    Button(modifier = Modifier, onClick = { expanded = !expanded }) {
                        Text(text = selectedOption)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = option  // Guarda la opción seleccionada
                                    expanded = false  // Cierra el menú después de la selección
                                },
                                text = { Text(text = option) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    salsas.forEachIndexed { index, salsa ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedSalsas[index],
                                onCheckedChange = { isChecked ->
                                    selectedSalsas[index] = isChecked
                                }
                            )
                            Text(fontSize = 10.sp, text = salsa)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Nota
                Text("Nota:")
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Escribe tu nota aquí") }
                )

                Spacer(modifier = Modifier.weight(1f))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Column(
                    ) {
                        Text(fontSize = 15.sp, fontWeight = FontWeight.Bold, text = "Cuenta total")
                        Text(
                            fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, text = "$${
                                NumberFormat.getInstance(
                                    Locale.getDefault()
                                ).format(item.price * quantity)
                            }"
                        )
                    }
                    // Cantidad
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text("-")
                        }
                        Text(text = "$quantity", modifier = Modifier.padding(horizontal = 8.dp))
                        Button(onClick = { quantity++ }) {
                            Text("+")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = {
                        val listSelectedSalsa:MutableList<String> = mutableListOf()
                        selectedSalsas.forEachIndexed { index, salsa ->
                            if (salsa){
                                listSelectedSalsa.add(salsas[index])
                            }
                        }

                        socketManager.newFood(
                            data = item,
                            listSalsa = listSelectedSalsa,
                            amount = item.price * quantity,
                            text = note.text,
                            duration = item.duration,
                            table = selectedOption.toInt(),
                            quantity = quantity
                        )
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        text = "Agregar Orden"
                    )
                }
            }
        }
    }
}