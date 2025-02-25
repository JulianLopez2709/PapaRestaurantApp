package jetpack.julian.ordenpapaapplication.Screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import jetpack.julian.ordenpapaapplication.core.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.navigation.NavigationWrapper
import jetpack.julian.ordenpapaapplication.core.socket.SocketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingResponde
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import jetpack.julian.ordenpapaapplication.ui.theme.OrdenPapaApplicationTheme

class MainActivity : ComponentActivity() {
    private val orders = mutableStateOf<List<OrderPreparingRespondeItem>>(emptyList())
    private val foodViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        socketManager = SocketManager()
        socketManager.emitOrder(){
            orders.value = it
        }
        setContent {
            val menu by foodViewModel.foods.observeAsState(emptyList())

            OrdenPapaApplicationTheme {
                NavigationWrapper(orders, menu)
            }
        }


        foodViewModel.getMenu()

    }

    private fun initViewModel() {

    }

    override fun onDestroy() {
        super.onDestroy()
        socketManager.disconnect()
    }
}

