package jetpack.julian.ordenpapaapplication

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.Socket
import io.socket.client.IO
import io.socket.emitter.Emitter
import jetpack.julian.ordenpapaapplication.model.food.Food
import java.net.URISyntaxException


class SocketManager {
    private lateinit var socket: Socket

    init {
        try {
            socket = IO.socket("http://")
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

    fun newFood(
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
            description = "${data.description}, note: $text, cantidad : $quantity, Salsas: ${listSalsa.map { it }}",
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
    }
}