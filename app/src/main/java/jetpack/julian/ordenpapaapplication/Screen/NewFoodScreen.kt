package jetpack.julian.ordenpapaapplication.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.Gray
import jetpack.julian.ordenpapaapplication.ui.theme.Principal
import java.text.NumberFormat
import java.util.Locale


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewScreen(item: Food, navController: NavHostController) {

    var selected by remember { mutableStateOf(false) }


    var price by remember {
        mutableIntStateOf(item.price)
    }

    var quantity by remember { mutableStateOf(1) }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    var expanded by remember { mutableStateOf(false) }
    var isForTwo by remember { mutableStateOf(false) }

    var isLlevar by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("1") }
    val options = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")


    val salsas = listOf(
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

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Principal)
            //.verticalScroll(scrollState)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 25.dp, start = 25.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            /*Button(
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
            }*/
            Text(
                text = "Nuevo Pedido",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    onClick = { expanded = !expanded },
                    border = null,
                    shape = RectangleShape
                ) {
                    Row {
                        Text(text = "Mesa ", color = Color.Black)
                        Text(text = selectedOption, color = Color.Black)
                    }
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOption = option
                                expanded = false
                            },
                            text = { Text(text = option) }
                        )
                    }
                }
            }
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
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, text = item.title)
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        text = "${item.duration * quantity}min"
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.DarkGray)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Salsas", fontWeight = FontWeight.SemiBold)
                        if (item.priceForTwo != null) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        Gray
                                    )
                            ) {
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("Para 2", fontWeight = FontWeight.Bold)
                                    Spacer(Modifier.width(5.dp))
                                    Switch(
                                        isForTwo, onCheckedChange = { check ->
                                            isForTwo = check
                                            if (isForTwo) price = item.priceForTwo else price =
                                                item.price
                                        },
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        maxItemsInEachRow = 4,
                        overflow = FlowRowOverflow.Clip
                    ) {
                        salsas.forEachIndexed { index, salsa ->
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


                Spacer(modifier = Modifier.height(8.dp))

                // Nota
                Text("Nota")
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Gray,
                    ),
                    placeholder = { Text("Escribe tu nota aquí") }
                )

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(10.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("¿Es para Llevar?", fontWeight = FontWeight.Bold)
                        Checkbox(checked = isLlevar, onCheckedChange = { isLlevar = !isLlevar })
                    }
                }

                Spacer(Modifier.height(5.dp))

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
                                ).format(price * quantity)
                            }"
                        )
                    }
                    // Cantidad
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Gray
                            )
                            .padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                onClick = { if (quantity > 1) quantity-- },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(Color.White),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.size(36.dp)
                            ) {
                                Text("-", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                            Text(
                                text = "$quantity",
                                modifier = Modifier.padding(horizontal = 12.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Button(
                                onClick = { quantity++ },
                                shape = CircleShape,
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.size(36.dp)
                            ) {
                                Text("+", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = {
                        val listSelectedSalsa: MutableList<String> = mutableListOf()
                        selectedSalsas.forEachIndexed { index, salsa ->
                            if (salsa) {
                                listSelectedSalsa.add(salsas[index])
                            }
                        }

                        socketManager.newFood(
                            data = item,
                            listSalsa = listSelectedSalsa,
                            amount = price * quantity,
                            text = "${note.text} ${if (isLlevar) "Es Para LLevar" else ""}",
                            duration = item.duration * quantity,
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