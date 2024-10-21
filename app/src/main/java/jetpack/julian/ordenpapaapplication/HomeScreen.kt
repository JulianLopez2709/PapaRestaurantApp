package jetpack.julian.ordenpapaapplication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, orders: MutableState<List<Food>>) {
    val foodsState = remember { mutableStateListOf<Food>() }

    LaunchedEffect(Unit) {
        socketManager.setupListeners { foods ->
            foodsState.clear()
            foodsState.addAll(foods.filter { it.isprocess })
        }
    }

    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(orders.value.filter { it.isprocess }) { item ->
                CardFood(item) {}
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}


@Composable
fun CardFood(item: Food, onClick: (() -> Unit)) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (item.img != null) {
                /*Image(
                    contentDescription = item.title,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape)
                )*/
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                val format = NumberFormat.getNumberInstance(Locale.getDefault()).format(item.price)
                Text(
                    text = "\$$format",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Button(
                onClick = { socketManager.updateFood(item) },
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Actualizar"
                )
            }
        }
    }
}


@Composable
fun BuildScreen(modifier: Modifier = Modifier) {
    Column {
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
        Text(text = "Cualquier cosa")
    }
}


