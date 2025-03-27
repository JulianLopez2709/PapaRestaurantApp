package jetpack.julian.ordenpapaapplication.Screen.Menu

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.Screen.Menu.component.CardFood
import jetpack.julian.ordenpapaapplication.Screen.Menu.component.OpenAddProduct
import jetpack.julian.ordenpapaapplication.Screen.Menu.component.OpenSaveOrder
import jetpack.julian.ordenpapaapplication.core.AddFoodRequest
import jetpack.julian.ordenpapaapplication.core.SelectFood
import jetpack.julian.ordenpapaapplication.core.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.foodDetail
import jetpack.julian.ordenpapaapplication.core.navigation.Home
import jetpack.julian.ordenpapaapplication.core.orderNew
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.BgDark
import jetpack.julian.ordenpapaapplication.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navHostController: NavHostController,
    menu: List<Food>,
    table: Int? = null,
    orderId: Int? = null
) {
    val listFood = menu.filter {
        it.type == "food" || it.type == "icecream" || it.type == "drink"
    } ?: emptyList()
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetCard by remember { mutableStateOf(false) }

    var selectedFood by remember { mutableStateOf<Food?>(null) }

    var selectedFoods = remember { mutableStateListOf<SelectFood>() }
    var listOrder: MutableList<Food> = mutableListOf()
    val listToppings = menu.filter { it.type == "topping" }

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
                containerColor = Color.White,
                modifier = Modifier.fillMaxHeight(0.75f),
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ) {
                selectedFood?.let {
                    OpenAddProduct(
                        toppingItems = listToppings,
                        foodItem = it,
                        onDismiss = { showBottomSheet = false },
                        saveProduct = { product, salsas, notes ->
                            selectedFoods.add(
                                SelectFood(
                                    food = product,
                                    salsas = salsas,
                                    notes = notes
                                )
                            )
                        }
                    )
                }
            }
        }


        if (showBottomSheetCard) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheetCard = false },
                sheetState = sheetState,
                containerColor = Color.White,
                modifier = Modifier.fillMaxHeight(0.75f),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                OpenSaveOrder(
                    listFoods = selectedFoods,
                    table = table!!,
                    onDelete = {
                        selectedFoods.remove(it)
                    }
                ) {
                    val listFood = mutableListOf<foodDetail>()
                    for (item in selectedFoods) {
                        listFood.add(
                            foodDetail(
                                food_id = item.food.food_id.toInt(),
                                extras = item.salsas,
                                notes = item.notes
                            )
                        )
                    }
                    if (orderId != null) {
                        socketManager.addFood(
                            data = AddFoodRequest(orderId, listFood),
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


