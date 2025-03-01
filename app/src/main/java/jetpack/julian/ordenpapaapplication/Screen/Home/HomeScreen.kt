package jetpack.julian.ordenpapaapplication.Screen.Home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import jetpack.julian.ordenpapaapplication.R
import jetpack.julian.ordenpapaapplication.Screen.Order.OrderScreen
import jetpack.julian.ordenpapaapplication.core.navigation.Menu
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import jetpack.julian.ordenpapaapplication.ui.theme.BgDark
import jetpack.julian.ordenpapaapplication.ui.theme.Principal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    orders: MutableState<List<OrderPreparingRespondeItem>>
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Menu")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Star)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.Star)
    var showTableDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
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
                actions = {
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Color.Yellow,
                onClick = {
                    showTableDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Icon Play Menu",
                )
            }
        },
    ) { innerPadding ->
        OrderScreen(
            modifier = Modifier
                .padding(innerPadding)
                .background(BgDark), orders = orders, navController
        )
    }


    if (showTableDialog) {
        Dialog(
            onDismissRequest = {
                showTableDialog = false

            },
            properties = DialogProperties(dismissOnClickOutside = true)
        ) {
            TableSelectionDialog() { table ->
                navController.navigate(Menu(table = table, orderId = null))
            }
        }
    }
}



