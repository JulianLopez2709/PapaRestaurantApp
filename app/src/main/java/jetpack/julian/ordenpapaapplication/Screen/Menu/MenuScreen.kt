package jetpack.julian.ordenpapaapplication.Screen.Menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.google.gson.Gson
import jetpack.julian.ordenpapaapplication.Screen.Menu.component.CardFood
import jetpack.julian.ordenpapaapplication.core.navigation.NewFood
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.BgDark

@Composable
fun MenuScreen(navHostController: NavHostController, menu: LiveData<List<Food>>) {
    val listFood = menu.value ?: emptyList()
    println("list desde el menu"+listFood)
    var searchText by remember { mutableStateOf("") }

    val filteredList = listFood.filter { foodItem ->
        foodItem.name.uppercase().contains(searchText.uppercase(), ignoreCase = true)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(BgDark)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = {
                Icon(tint = Color.Gray, imageVector = Icons.Outlined.Search, contentDescription = null)
            },
            label = { Text("Buscar comida") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor  = Color.Gray,
                )
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid (
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        )  {
            items(filteredList) { foodItem ->
                CardFood(item = foodItem, onClick = {
                    val jsonString = Gson().toJson(foodItem)
                    navHostController.navigate(NewFood(jsonString))
                })
            }
        }
    }
}



@Composable
fun ShowModal(item: Food, onDismiss: () -> Unit) {


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Detalles de ${item.name}") },
        text = {

        },
        containerColor = Color.White,
        confirmButton = {
            /*Button(onClick = {
                val selectedSalsasNames = salsas.filterIndexed { index, _ -> selectedSalsas[index] }
                socketManager.newFood(item, quantity, note.text)
                onDismiss()
            }) {
                Text("Aceptar")
            }*/
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}