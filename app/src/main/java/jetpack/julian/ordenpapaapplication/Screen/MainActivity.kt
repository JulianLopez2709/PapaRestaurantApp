package jetpack.julian.ordenpapaapplication.Screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import jetpack.julian.ordenpapaapplication.core.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.navigation.NavigationWrapper
import jetpack.julian.ordenpapaapplication.core.socket.SocketManager
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
        foodViewModel.getMenu()
        setContent {
            val menu = foodViewModel.foods

            println("lista desde main "+ menu.value)

            OrdenPapaApplicationTheme {
                NavigationWrapper(orders, menu)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketManager.disconnect()
    }
}

