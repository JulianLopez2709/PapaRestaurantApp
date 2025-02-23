package jetpack.julian.ordenpapaapplication.model.order.OrderPreparing

data class Food(
    val available: Boolean,
    val createdAt: String,
    val description: String,
    val food_id: Int,
    val name: String,
    val price: Int,
    val type: String,
    val updatedAt: String,
    val img : String ? = null
)