package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetpack.julian.ordenpapaapplication.core.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.util.Locale


@Composable
fun CardFood(item: Food, onClick: (() -> Unit)) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
            /*.clickable {
                socketManager.updateFood(item)
            }*/,
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
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


            Box(Modifier.run { fillMaxWidth().height(45.dp).background(color = Color.Green) })
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color.Gray
            )
            val format = NumberFormat.getNumberInstance(Locale.getDefault()).format(item.price)
            Text(
                text = "\$$format",
                fontSize = 19.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow
            )

        }
    }
}
