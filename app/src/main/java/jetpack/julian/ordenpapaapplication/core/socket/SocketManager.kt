package jetpack.julian.ordenpapaapplication.core.socket

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.Socket
import io.socket.client.IO
import jetpack.julian.ordenpapaapplication.core.AddFoodRequest
import jetpack.julian.ordenpapaapplication.core.Utils
import jetpack.julian.ordenpapaapplication.core.orderNew
import jetpack.julian.ordenpapaapplication.core.patch
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingResponde
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import java.net.URISyntaxException


class SocketManager {
    private lateinit var socket: Socket

    init {
        try {
            socket = IO.socket(Utils.BASE_URL)
            onCreate()
        } catch (e:URISyntaxException){
            e.printStackTrace()
        }
    }

    private fun onCreate(){
        socket.on(Socket.EVENT_CONNECT) {
            Log.e("tag","Conectado al servidor")
        }
        socket.on(Socket.EVENT_DISCONNECT) {
            println("Desconectado del servidor")
        }

        socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
            Log.e("tag", "Error de conexión: ${args[0]}")
        }

        socket.connect()
   }


    /**
     * {
     *   "user_id": 1,
     *   "foods": [
     *     {
     *       "food_id": 2,
     *       "extras": [
     *         "De la casa",
     *         "Chicharon"
     *       ]
     *     },
     *     {
     *       "food_id": 4,
     *       "extras": [
     *         "queso"
     *       ]
     *     }
     *   ]
     * }
     */


    fun newOrder(data: orderNew){
        val json = Gson().toJson(data)
        socket.emit("client:newOrder",json)
    }


    fun addFood(data  : AddFoodRequest){
        val json = Gson().toJson(data)
        socket.emit("client:addOrder", json)
    }


    fun patchStatus(data  : patch){
        val json = Gson().toJson(data)
        socket.emit("client:patchStatus", json)
    }

    fun emitOrder(callback: (List<OrderPreparingRespondeItem>) -> Unit ) {
        socket.on("server:loadOrder") { args ->
            if (args.isNotEmpty()) {
                val data = args[0]
                println(data)
                data?.let {
                    val listFood: List<OrderPreparingRespondeItem> =
                        Gson().fromJson(it.toString(), object : TypeToken<List<OrderPreparingRespondeItem>>() {}.type)
                    callback(listFood)
                }
            } else {
                Log.e("tag", "No data received")
            }
        }
    }


    fun disconnect(){
        socket.disconnect()
    }

    fun updateFood(food:Food){
        val json = Gson().toJson(food)
        socket.emit("client:update",json)
    }

    /*fun newFood(
        data: Food,
        quantity: Int,
        text: String,
        table: Int = 1,
        duration: Int = 1,
        amount: Int,
        listSalsa: MutableList<String>
    ){
        val food = Food(
            duration = duration,
            table = table,
            title = data.title,
            description = "${data.description} > $text > $quantity > $listSalsa",
            isprocess = data.isprocess,
            price = amount
            )
        val json = Gson().toJson(food)
        socket.emit("client:newfood",json)
    }


    fun testSocket(txt : String){
        socket.emit("client:test",txt)
    }*/
}