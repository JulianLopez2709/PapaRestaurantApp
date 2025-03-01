package jetpack.julian.ordenpapaapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.gson.Gson
import jetpack.julian.ordenpapaapplication.Screen.Auth.LoginScreen
import jetpack.julian.ordenpapaapplication.Screen.Home.HomeScreen
import jetpack.julian.ordenpapaapplication.Screen.Menu.MenuScreen
import jetpack.julian.ordenpapaapplication.Screen.Order.OrderDetailScreen
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem

@Composable
fun NavigationWrapper(orders: MutableState<List<OrderPreparingRespondeItem>>, menu: List<Food>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Login> {
            LoginScreen(navController)
        }

        composable<Home> {
            HomeScreen(navController, orders)
        }

        composable<Menu> {
            val arg = it.toRoute<Menu>()
            MenuScreen(navController, menu, table = arg.table, orderId = arg.orderId)
        }

        composable<OrderDetail> {
            val args = it.toRoute<OrderDetail>()
            val orderJson = Gson().fromJson(args.detail, OrderPreparingRespondeItem::class.java)
            OrderDetailScreen(orderJson,navController)
        }

        /*
        composable<NewFood> {
            val jsonString = it.toRoute<String>()
            val json = Gson().fromJson<Food>(jsonString, object : TypeToken<Food>() {}.type)
            NewScreen(json, navController)
        }*/
    }
}
