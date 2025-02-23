package jetpack.julian.ordenpapaapplication.core.api

import jetpack.julian.ordenpapaapplication.model.food.Food
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/food")
    suspend fun getMenu():List<Food>
}