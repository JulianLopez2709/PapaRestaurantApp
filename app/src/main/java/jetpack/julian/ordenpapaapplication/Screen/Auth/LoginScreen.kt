package jetpack.julian.ordenpapaapplication.Screen.Auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.core.navigation.Order

@Composable
fun LoginScreen(navController: NavHostController) {
    Log.d("NAVIGATION", "Renderizando LoginScreen") // <-- Verifica si se muestra en Logcat

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
            .clickable {
                Log.d("NAVIGATION", "Click en el fondo")
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
            Log.d("NAVIGATION", "Click en el botón") // <-- Si esto no aparece, el onClick no funciona
            Toast.makeText(context, "ds", Toast.LENGTH_SHORT).show()
                navController.navigate(Order)
        }) {
            Text("Navigate Order")
        }


        Text("Gime me Click", modifier =  Modifier.clickable {
            Log.d("NAVIGATION", "Click en el texto") // <-- Si esto no aparece, el onClick no funciona

        })
    }
}