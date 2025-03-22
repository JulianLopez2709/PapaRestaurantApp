package jetpack.julian.ordenpapaapplication.Screen.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jetpack.julian.ordenpapaapplication.core.Utils.formatPrice

@Composable
fun TotalPrice(sizeOrder : Int, totalPrice:Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.Gray))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total x$sizeOrder", fontWeight = FontWeight.Bold)
            Row {
                Text("$", color = Color.Green,fontWeight = FontWeight.Bold)
                Text(formatPrice(totalPrice.toDouble()),fontWeight = FontWeight.Bold)
            }
        }
    }
}