package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.Yellow


@Composable
fun OpenAddProduct(
    toppingItems: List<Food>,
    foodItem: Food,
    onDismiss: () -> Unit,
    saveProduct: (Food, List<String>, String?, TotalPrice : Double) -> Unit
) {

    var notes by remember { mutableStateOf("") }
    var isLlevar by remember { mutableStateOf(false) }
    val selectedSalsas = remember { mutableStateListOf<String>() }
    val totalPrice = remember { mutableStateOf(0.0) }
    val selectedToppings = remember { mutableStateOf(mutableSetOf<Food>()) }

    val scrollState = rememberScrollState()

    fun saveTopping() {
        totalPrice.value = foodItem.price
        selectedToppings.value.forEach { topping ->
            totalPrice.value += topping.price
            when (topping.name) {
                "Carne desmechada" -> notes += "Extra Carne Desmechada, "
                "Pollo desmechado" -> notes += "Extra Pollo Desmechado, "
                "Pollo crunch" -> notes += "Extra Pollo crunch, "
                "Chicharrón" -> notes += "Extra Chicharrón, "
                "Camarones" -> notes += "Extra Camarones, "
                "Tocineta" -> notes += "Extra Tocineta, "
                "Salchicha" -> notes += "Extra Salchicha, "
                "Chorizo" -> notes += "Extra Chorizo, "
                "Costilla" -> notes += "Extra Costilla, "
                "Huevos de codorniz (3)" -> notes += "Extra Huevos de codorniz (3), "
                "Madurito" -> notes += "Extra Madurito, "
                "Maicitos" -> notes += "Extra Maicitos, "
                "Queso mozzarella" -> notes += "Extra Queso mozzarella, "
                "Pico de gallo" -> notes += "Extra Pico de gallo, "
                "Porción de papa" -> notes += "Extra Porción de papa, "
                "Porción de papa criolla" -> notes += "Extra Porción de papa criolla, "
                else -> ""
            }
        }
        saveProduct(foodItem, selectedSalsas, notes, totalPrice.value)
        onDismiss()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)

        //.verticalScroll(scrollState)
    ) {
        Text(
            text = foodItem.name,
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))


        if (foodItem.type != "drink") {
            Text("Seleccione las Salsas")

            SalsasComponent(foodItem.type) { selected ->
                selectedSalsas.clear()
                selectedSalsas.addAll(selected)
            }

            if (foodItem.type == "food") {
                Text("Toppings")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(toppingItems) {
                        val isSelected = it in selectedToppings.value

                        Column(
                            Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    selectedToppings.value =
                                        selectedToppings.value.toMutableSet().apply {
                                            if (contains(it)) remove(it) else add(it)
                                        }
                                }
                                .background(if (isSelected) Yellow.copy(alpha = 0.5f) else Color.White)
                                .border(
                                    1.dp,
                                    if (isSelected) Yellow else Color.Gray,
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = it.name,
                                lineHeight = 2.sp,
                                modifier = Modifier,
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "$${it.price}",
                                modifier = Modifier,
                                lineHeight = 2.sp,
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nota
        Text("Nota")
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            maxLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
            ),
            placeholder = { Text("Escribe tu nota aquí") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                saveTopping()
            }
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                text = "Agregar Producto"
            )
        }
    }
}