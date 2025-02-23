package jetpack.julian.ordenpapaapplication.core.socket

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.Socket
import io.socket.client.IO
import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingResponde
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import java.net.URISyntaxException


class SocketManager {
    private lateinit var socket: Socket

    init {
        try {
            socket = IO.socket("http://192.168.56.1:3000")
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

    fun setupListeners(callback:(List<Food>)->Unit ) {
        socket.on("server:loadfood") { args ->
            Log.e("tag", "load food: ${args[0]}")

            if (args.isNotEmpty()) {
                val data = args[0]
                data?.let {
                    val listFood: List<Food> =
                        Gson().fromJson(it.toString(), object : TypeToken<List<Food>>() {}.type)
                    callback(listFood)
                } ?: run {
                    Log.e("tag", "Data received is not a String")
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


    fun deleteFood(food:String){
        socket.on("client:newfood", Emitter.Listener {

        })
    }

    fun emitFood(){
        socket.on("server:loadfood",Emitter.Listener {
                Log.e("tag", "data: $it")
        })
    }

    fun testSocket(txt : String){
        socket.emit("client:test",txt)
    }*/
}