package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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


@Composable
fun OpenAddProduct(
    foodItem: Food,
    onDismiss: () -> Unit,
    saveProduct: (Food, List<String>, String?) -> Unit
) {

    var notes by remember { mutableStateOf("") }
    var isLlevar by remember { mutableStateOf(false) }
    val selectedSalsas = remember { mutableStateListOf<String>() }

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


        if (foodItem.type != "drink") {
            Text("Seleccione las Salsas")
            SalsasComponent(foodItem.type) { selected ->
                selectedSalsas.clear()
                selectedSalsas.addAll(selected)
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
                .fillMaxWidth().fillMaxHeight(0.5f),
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
                saveProduct(foodItem, selectedSalsas,notes)
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