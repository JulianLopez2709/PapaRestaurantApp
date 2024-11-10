package jetpack.julian.ordenpapaapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.gson.Gson
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.Utils
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.navigation.NewFood
import jetpack.julian.ordenpapaapplication.model.food.Food

@Composable
fun MenuScreen(navHostController: NavHostController) {
    val listFood = Utils.menu
    var searchText by remember { mutableStateOf("") }

    val filteredList = listFood.filter { foodItem ->
        foodItem.title.uppercase().contains(searchText.uppercase(), ignoreCase = true)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp)
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

        LazyColumn {
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
        title = { Text("Detalles de ${item.title}") },
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