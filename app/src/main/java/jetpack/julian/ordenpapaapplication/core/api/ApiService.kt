package jetpack.julian.ordenpapaapplication.core.api

import jetpack.julian.ordenpapaapplication.model.food.Food
import jetpack.julian.ordenpapaapplication.model.order.OrderPreparing.OrderPreparingRespondeItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/food")
    suspend fun getMenu():List<Food>

    @GET("/order/day")
    suspend fun getOrdersDay(): List<OrderPreparingRespondeItem>
}