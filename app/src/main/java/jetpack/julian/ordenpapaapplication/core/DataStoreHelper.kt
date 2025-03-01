package jetpack.julian.ordenpapaapplication.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jetpack.julian.ordenpapaapplication.model.food.Food
import kotlinx.coroutines.flow.first


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

class DataStoreHelper (private val context:Context) {

    private val dataStore = context.dataStore

    companion object{
        private val PRODUCTS_KEY = stringPreferencesKey("products")
    }

    suspend fun saveProducts(products: List<Food>) {
        val json = Gson().toJson(products)
        dataStore.edit { preferences ->
            preferences[PRODUCTS_KEY] = json
        }
    }

    suspend fun getProducts(): List<Food> {
        val preferences = dataStore.data.first()
        val json = preferences[PRODUCTS_KEY] ?: return emptyList()
        return Gson().fromJson(json, object : TypeToken<List<Food>>() {}.type)
    }

}