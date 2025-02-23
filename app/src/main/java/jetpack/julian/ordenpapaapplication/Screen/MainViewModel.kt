package jetpack.julian.ordenpapaapplication.Screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jetpack.julian.ordenpapaapplication.core.api.ApiInstance
import jetpack.julian.ordenpapaapplication.model.food.Food
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _foods: MutableLiveData<List<Food>>
        get() = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    fun getMenu() {
        viewModelScope.launch {
            try {
                val res = ApiInstance.api.getMenu()
                _foods.postValue(res)
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al obtener los alimentos", e)
            }
        }
    }
}