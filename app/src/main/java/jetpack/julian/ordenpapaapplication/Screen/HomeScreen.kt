package jetpack.julian.ordenpapaapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
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
                    .fillMaxWidth()
                    .padding(7.dp),
                colors = TopAppBarDefaults.topAppBarColors(Color.White),
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(Color.Black),
                        onClick = { }
                    ) {
                        Icon(
                            modifier = Modifier,
                            tint = Color.Black,
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Image(
                        modifier = Modifier.height(60.dp),
                        painter = painterResource(id = R.drawable.lapapalogo),
                        contentDescription = "logo papa"
                    )
                },
                actions = { }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item
                            )
                        },
                        label = { Text(item, color = Color.White) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
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


