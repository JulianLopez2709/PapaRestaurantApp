package jetpack.julian.ordenpapaapplication.Screen.Menu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.core.Utils
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.util.Locale


@Composable
fun CardFood(item: Food, onClick: (() -> Unit)) {

    val bgCard =
    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
        /*.clickable {
            socketManager.updateFood(item)
        }*/,
        colors = CardDefaults.cardColors(
            containerColor = if (item.type == "icecream") Color(0xFF6568FF) else Color.White
        )

    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
        ) {
            val imageRes = when(item.type){
                "drink"-> R.drawable.drink
                "icecream" -> R.drawable.icecream
                else -> R.drawable.fries
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

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
            val format = Utils.formatPrice(item.price)
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                text = "\$$format",
                fontSize = 19.sp,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

        }
    }
}
