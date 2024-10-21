package jetpack.julian.ordenpapaapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import jetpack.julian.ordenpapaapplication.model.food.Food

object Utils {
    var useFood : List<Food> = emptyList()
    lateinit var socketManager: SocketManager
    val menu: List<Food> = listOf(
        Food(
            title = "CORDON BLUE",
            description = "Un plato típico de Colombia con frijoles, arroz, carne, y plátano.",
            price = 25000,
        ),
        Food(
            title = "Pizza Margherita",
            description = "Pizza clásica con tomate, mozzarella y albahaca.",
            price = 15500,
        ),
        Food(
            title = "Sushi Variado",
            description = "Selección de sushi fresco y sashimi.",
            price = 30000,
        ),
        Food(
            title = "Tacos al Pastor",
            description = "Deliciosos tacos con cerdo adobado y piña.",
            price = 12000,
        ),
        Food(
            title = "Pasta Carbonara",
            description = "Pasta con salsa cremosa, panceta y queso parmesano.",
            price = 18000,
        ),
        Food(
            title = "Ensalada César",
            description = "Ensalada fresca con lechuga, pollo, y aderezo César.",
            price = 10000,
        ),
        Food(
            title = "Sushi Variado",
            description = "Selección de sushi fresco y sashimi.",
            price = 30000,
        ),
        Food(
            title = "Tacos al Pastor",
            description = "Deliciosos tacos con cerdo adobado y piña.",
            price = 12000,
        ),
        Food(
            title = "Pasta Carbonara",
            description = "Pasta con salsa cremosa, panceta y queso parmesano.",
            price = 18000,
        ),
        Food(
            title = "Ensalada César",
            description = "Ensalada fresca con lechuga, pollo, y aderezo César.",
            price = 10000,
        ),
    )
}