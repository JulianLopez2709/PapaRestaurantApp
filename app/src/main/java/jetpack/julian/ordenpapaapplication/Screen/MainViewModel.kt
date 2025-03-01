package jetpack.julian.ordenpapaapplication.Screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jetpack.julian.ordenpapaapplication.core.DataStoreHelper
import jetpack.julian.ordenpapaapplication.core.api.ApiInstance
import jetpack.julian.ordenpapaapplication.model.food.Food
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataStore : DataStoreHelper
) : ViewModel() {
    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods


    init {
        getMenu()
    }

    private fun getMenu() {
        viewModelScope.launch {
            try {
                val cachedFoods = dataStore.getProducts()
                if (cachedFoods.isNotEmpty()) {
                    _foods.postValue(cachedFoods)
                } else {
                    val res = ApiInstance.api.getMenu()
                    _foods.postValue(res)
                    dataStore.saveProducts(res)
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al obtener los alimentos", e)
            }
        }
    }
}