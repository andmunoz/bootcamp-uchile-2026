package cl.uchile.dcc.mobile.foodregistry.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object PreferencesKeys {
    val THEME_MODE = stringPreferencesKey("TemaActual")
    val USERNAME = stringPreferencesKey("NombreUsuario")
    val LANGUAGE = stringPreferencesKey("Idioma")
    val HOUR_FORMAT = stringPreferencesKey("FormatoHora")
}

class FoodRegistryAppRepository(
    private val context: Context,
    private val config: DataStore<Preferences>,
    private val values: SharedPreferences
) {

    val theme: Flow<String> = config.data
        .map { preferences ->
            preferences[PreferencesKeys.THEME_MODE] ?: "Auto"
        }

    suspend fun setTheme(theme: String) {
        config.edit { preferences ->
            preferences[PreferencesKeys.THEME_MODE] = theme
        }
    }

    val name: Flow<String> = config.data
        .map { preferences ->
            preferences[PreferencesKeys.USERNAME] ?: "Anónimo"
        }

    suspend fun setName(name: String) {
        config.edit { preferences ->
            preferences[PreferencesKeys.USERNAME] = name
        }
    }

    fun getCalories(): Int {
        return values.getInt("CaloriasDiarias", 2000)
    }

    fun setCalories(calories: Int) {
        values.edit().putInt("CaloriasDiarias", calories).apply()
    }

    fun getCarbohydrates(): Int {
        return values.getInt("CarbohidratosDiarios", 2000)
    }

    fun setCarbohydrates(calories: Int) {
        values.edit().putInt("CarbohidratosDiarios", calories).apply()
    }
}