package jetpack.julian.ordenpapaapplication.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home;

@Serializable
data class Menu(
    val table: Int
)

@Serializable
object Shop;

@Serializable
object Order;

@Serializable
object OrderDetail;

@Serializable
object Build;

@Serializable
data class NewFood(
    val food: String
);


@Serializable
object Login;