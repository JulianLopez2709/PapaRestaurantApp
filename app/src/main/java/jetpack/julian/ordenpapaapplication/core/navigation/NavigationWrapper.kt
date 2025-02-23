package jetpack.julian.ordenpapaapplication.core.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jetpack.julian.ordenpapaapplication.Screen.Home.HomeScreen
import jetpack.julian.ordenpapaapplication.Screen.Auth.LoginScreen
import jetpack.julian.ordenpapaapplication.Screen.Menu.MenuScreen
import jetpack.julian.ordenpapaapplication.Screen.NewScreen
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingResponde
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapper(orders: MutableState<List<OrderPreparingRespondeItem>>, menu: LiveData<List<Food>>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Login> {
            LoginScreen(navController)
        }

        composable<Home> {
            HomeScreen(navController, orders)
        }

        composable<Menu> {
            MenuScreen(navController, menu)
        }

        composable<NewFood> {
            val jsonString = it.toRoute<NewFood>()
            val json = Gson().fromJson<Food>(jsonString.food, object : TypeToken<Food>() {}.type)
            NewScreen(json, navController)
        }
    }
}
