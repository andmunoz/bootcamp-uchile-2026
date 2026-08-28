package cl.uchile.dcc.mobile.foodregistry.data.repository

import android.content.SharedPreferences

class FoodRegistryAppRepository(
    private val config: SharedPreferences,
    private val values: SharedPreferences
) {
    // SharedPreferences: ==> (Clave, Valor)
    // Clave: String que representa el nombre de la preferencia ("TemaActual")
    // Valor: Escalar que representa el valor asodiado a la prefencia ("Oscuro", "Claro")
    // Workspace: ==> Nombre del Espacio de SharedPreferences

    fun getTheme(): String {
        return config.getString("TemaActual", "Auto")?:"Auto"
    }

    fun setTheme(theme: String) {
        config.edit().putString("TemaActual", theme).apply()
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