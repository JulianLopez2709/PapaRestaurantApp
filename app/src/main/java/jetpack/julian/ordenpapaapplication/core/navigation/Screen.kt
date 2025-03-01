package jetpack.julian.ordenpapaapplication.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home;

@Serializable
data class Menu(
    val table: Int?,
    val orderId:Int?
)

@Serializable
object Shop;

@Serializable
object Order;

@Serializable
data class OrderDetail(
    val detail : String
);

@Serializable
object Build;

@Serializable
data class NewFood(
    val food: String
);


@Serializable
object Login;