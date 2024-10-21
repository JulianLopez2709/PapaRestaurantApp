package jetpack.julian.ordenpapaapplication.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jetpack.julian.ordenpapaapplication.HomeScreen
import jetpack.julian.ordenpapaapplication.MenuScreen
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.model.food.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapper(orders: MutableState<List<Food>>) {

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Menu")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Star)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.Star)
    val navController = rememberNavController()

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
                title = { Image(modifier = Modifier.height(60.dp),painter = painterResource(id = R.drawable.lapapalogo), contentDescription = "logo papa") },
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
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> navController.navigate(Home)
                                1 -> navController.navigate(Menu)
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Home) {
            composable<Home> {
                HomeScreen(Modifier.padding(paddingValues), orders)
            }
            composable<Menu> {
                MenuScreen(Modifier.padding(paddingValues))
            }
        }
    }
}
