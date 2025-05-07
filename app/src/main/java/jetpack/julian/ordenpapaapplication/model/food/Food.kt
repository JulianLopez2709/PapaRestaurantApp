package jetpack.julian.ordenpapaapplication.model.food

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val food_id: String,
    val description: String,
    val price: Double,
    val name: String,
    val type: String,
    val img: String ? = null,
)

enum class FoodType(val type: String) {
    FOOD("food"),
    DRINK("drink"),
    EXTRAS("extras"),
    SALSAS("salsas"),
}


