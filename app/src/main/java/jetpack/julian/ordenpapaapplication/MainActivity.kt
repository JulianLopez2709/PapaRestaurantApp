package jetpack.julian.ordenpapaapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import jetpack.julian.ordenpapaapplication.Utils.socketManager
import jetpack.julian.ordenpapaapplication.core.navigation.NavigationWrapper
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.ui.theme.OrdenPapaApplicationTheme

class MainActivity : ComponentActivity() {
    private val orders = mutableStateOf<List<Food>>(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        socketManager = SocketManager()
        socketManager.setupListeners {
            orders.value = it
        }
        setContent {
            OrdenPapaApplicationTheme {
                NavigationWrapper(orders)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketManager.disconnect()
    }
}

