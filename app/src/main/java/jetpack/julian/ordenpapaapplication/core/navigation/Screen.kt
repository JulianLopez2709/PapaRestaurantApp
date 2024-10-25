package jetpack.julian.ordenpapaapplication.core.navigation

import jetpack.julian.ordenpapaapplication.model.food.Food
import kotlinx.serialization.Serializable

@Serializable
object Home;

@Serializable
object Menu;

@Serializable
object Order;

@Serializable
object Build;

@Serializable
data class NewFood(
    val food : String
);


@Serializable
object Login;