package jetpack.julian.ordenpapaapplication.Screen.Menu

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.Screen.Menu.component.CardFood
import jetpack.julian.ordenpapaapplication.Screen.Menu.component.SalsasComponent
import jetpack.julian.ordenpapaapplication.core.AddFoodRequest
import jetpack.julian.ordenpapaapplication.core.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.foodDetail
import jetpack.julian.ordenpapaapplication.core.navigation.Home
import jetpack.julian.ordenpapaapplication.core.orderNew
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.BgDark
import jetpack.julian.ordenpapaapplication.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navHostController: NavHostController, menu: List<Food>, table: Int? = null,orderId: Int? = null ) {
    val listFood = menu ?: emptyList()
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetCard by remember { mutableStateOf(false) }

    var selectedFood by remember { mutableStateOf<Food?>(null) }

    var selectedFoods = remember { mutableStateListOf<Food>() }
    var listOrder: MutableList<Food> = mutableListOf()

    val filteredList = listFood.filter { foodItem ->
        foodItem.name.uppercase().contains(searchText.uppercase(), ignoreCase = true)
    }

    Box(

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgDark)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    leadingIcon = {
                        Icon(
                            tint = Color.Gray,
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    label = { Text("Buscar comida") },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Gray,
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    modifier = Modifier
                        .width(80.dp)
                        .height(55.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Yellow),
                    onClick = {
                        if (selectedFoods.isNotEmpty()) {
                            showBottomSheetCard = true
                        }
                    }
                ) {
                    BadgedBox(
                        badge = {
                            if (selectedFoods.isNotEmpty()) {
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ) {
                                    Text("${selectedFoods.size}")
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "",
                            tint = Purple40,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredList) { foodItem ->
                    CardFood(item = foodItem, onClick = {
                        selectedFood = foodItem
                        showBottomSheet = true
                    })
                }
            }


        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                modifier = Modifier.fillMaxHeight(0.75f),
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ) {
                selectedFood?.let {
                    OpenAddProduct(
                        foodItem = it,
                        onDismiss = { showBottomSheet = false },
                        saveProduct = { product ->
                            selectedFoods.add(product)
                        }
                    )
                }
            }
        }


        if (showBottomSheetCard) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheetCard = false },
                sheetState = sheetState,
                modifier = Modifier.fillMaxHeight(0.75f),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                OpenSaveOrder(
                    listFoods = selectedFoods,
                    table = table!!
                ) {
                    val listFood = mutableListOf<foodDetail>()
                    for (item in selectedFoods){
                        listFood.add(foodDetail(
                            food_id = item.food_id.toInt(),
                            extras = listOf("Tomate", "Cebolla")
                        ))
                    }
                    if (orderId != null) {
                        socketManager.addFood(
                            data = AddFoodRequest(orderId,listFood),
                        )
                    } else {
                        socketManager.newOrder(
                            data = orderNew(user_id = 1, foods = listFood, table = table),
                        )
                    }
                    navHostController.navigate(Home)
                }
            }
        }
    }
}


@Composable
fun OpenAddProduct(foodItem: Food, onDismiss: () -> Unit, saveProduct: (Food) -> Unit) {

    var selected by remember { mutableStateOf(false) }
    var note by remember { mutableStateOf(TextFieldValue("")) }
    var isLlevar by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("1") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)

        //.verticalScroll(scrollState)
    ) {
        Text(
            text = foodItem.name,
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))


        if (foodItem.type == "food") {
            Text("Seleccione las Salsas")
            SalsasComponent()
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nota
        Text("Nota")
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            maxLines = 3,
            modifier = Modifier
                .fillMaxWidth(),
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
        Spacer(modifier = Modifier.height(20.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                saveProduct(foodItem)
                onDismiss()
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


@Composable
fun OpenSaveOrder(listFoods: List<Food>,  table: Int, clickable: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        Text("Mesa $table")
        Spacer(Modifier.height(7.dp))
        Text("Lista de productos")

        for (item in listFoods) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(

                ) {
                    Text(item.name)
                }

                Button(
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    shape = RectangleShape,
                    onClick = {
                    }
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete Food")
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                clickable()
            }
        ) {
            Text("Enviar Order")
        }
    }
}
