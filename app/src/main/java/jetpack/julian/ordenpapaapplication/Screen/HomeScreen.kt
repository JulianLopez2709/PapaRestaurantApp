package jetpack.julian.ordenpapaapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.BgDark
import jetpack.julian.ordenpapaapplication.ui.theme.Principal
import jetpack.julian.ordenpapaapplication.ui.theme.Yellow
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, orders: MutableState<List<Food>>) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Menu")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Star)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.Star)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(Principal),
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.height(60.dp),
                            painter = painterResource(id = R.drawable.lapapalogo02),
                            contentDescription = "logo papa"
                        )
                        Spacer(Modifier.width(15.dp))
                        Text(
                            text = "La papa prohibida",
                            color = Color.Yellow,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    }
                },
                actions = { }
            )
        },
        bottomBar = {
            NavigationBar(
                Modifier.fillMaxWidth(),
                containerColor = Principal,
                contentColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = selectedItem == index
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (isSelected) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item,
                                tint = if (isSelected) Color.White else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                text = item,
                                color = if (isSelected) Yellow else Color.Gray,
                                style = if (isSelected) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                else MaterialTheme.typography.bodyMedium
                            )
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = if (isSelected) Yellow else Color.Black
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).background(BgDark)) {
            when (selectedItem) {
                0 -> OrderScreen(modifier = Modifier.padding(innerPadding), orders= orders)
                1 -> MenuScreen(navController)
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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
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
                    .size(48.dp),
                contentPadding = PaddingValues(0.dp),
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


