package jetpack.julian.ordenpapaapplication.core

import jetpack.julian.ordenpapaapplication.core.socket.SocketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.util.Locale

object Utils {
    var useFood: List<Food> = emptyList()
    val BASE_URL = "http://192.168.121.142:3000"
    lateinit var socketManager: SocketManager

    fun formatPrice (price:Double): String {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(price)
    }

}


data class orderNew(
    val table : Int,
    val user_id : Int,
    val foods :List<foodDetail>
)

data class AddFoodRequest(
    val order_id : Int,
    val foods :List<foodDetail>
)

data class foodDetail(
    val food_id:Int,
    val extras: List<String>
)

data class patch(
    val orderId : Int,
    val status : String
)