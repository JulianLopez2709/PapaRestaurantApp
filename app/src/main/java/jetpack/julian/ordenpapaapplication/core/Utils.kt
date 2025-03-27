package jetpack.julian.ordenpapaapplication.core

import jetpack.julian.ordenpapaapplication.core.socket.SocketManager
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object Utils {
    var useFood: List<Food> = emptyList()
    val BASE_URL = "http://192.168.20.16:3001"
    lateinit var socketManager: SocketManager

    fun formatPrice (price:Double): String {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(price)
    }


    fun String.toFormattedDate(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Asegurar que interpreta la fecha en UTC
        val outputFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault() // Convertir a la zona horaria local

        val date: Date? = inputFormat.parse(this)
        return date?.let { outputFormat.format(it) } ?: "Invalid Date"
    }

}

data class SelectFood(
    val food: Food,
    val salsas : List<String>,
    val notes : String?
)

data class orderNew(
    val table : Int,
    val user_id : Int,
    val foods :List<foodDetail>
)

data class AddFoodRequest(
    val order_id : Int,
    val foods :List<foodDetail>
)

//Respondes about web socket
data class foodDetail(
    val food_id:Int,
    val extras: List<String>,
    val notes : String?
)

data class patch(
    val orderId : Int,
    val status : String
)