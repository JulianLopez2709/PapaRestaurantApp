package jetpack.julian.ordenpapaapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food

@Composable
fun MenuScreen(modifier: Modifier = Modifier) {
    val listFood = Utils.menu
    var selectedItem by remember { mutableStateOf<Food?>(null) }
    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(listFood){
                CardFood(item = it, onClick = {
                    selectedItem = it
                })
            }
        }

        selectedItem?.let {
            ShowModal(item = it) { selectedItem = null }
        }
    }
}



@Composable
fun ShowModal(item: Food, onDismiss: () -> Unit) {
    var quantity by remember { mutableStateOf(1) }
    var note by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Detalles de ${item.title}") },
        text = {
            Column {
                // Imagen del alimento
                    // Aquí puedes usar una imagen
                    Image(
                        painter = painterResource(id = R.drawable.newproduct),
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray)
                    )

                Text(text = "Descripción: ${item.description}")
                Text(text = "Precio: \$${item.price}")

                Spacer(modifier = Modifier.height(8.dp))

                // Cantidad
                Text("Cantidad:")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
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

                Spacer(modifier = Modifier.height(8.dp))

                // Nota
                Text("Nota:")
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Escribe tu nota aquí") }
                )
            }
        },
        containerColor = Color.White,
        confirmButton = {
            Button(onClick = {
                socketManager.newFood(item, quantity, note.text)
                onDismiss()
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}