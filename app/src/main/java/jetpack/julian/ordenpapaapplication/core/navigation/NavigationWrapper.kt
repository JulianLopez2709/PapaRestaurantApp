package jetpack.julian.ordenpapaapplication.core.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jetpack.julian.ordenpapaapplication.Screen.HomeScreen
import jetpack.julian.ordenpapaapplication.Screen.LoginScreen
import jetpack.julian.ordenpapaapplication.Screen.NewScreen
import jetpack.julian.ordenpapaapplication.model.food.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationWrapper(orders: MutableState<List<Food>>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Login> {
            LoginScreen()
        }

        composable<Home> {
            HomeScreen(navController, orders)
        }

        composable<NewFood> {
            val jsonString = it.toRoute<NewFood>()
            val json = Gson().fromJson<Food>(jsonString.food, object : TypeToken<Food>() {}.type)
            NewScreen(json, navController)
        }
    }
}
