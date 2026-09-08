package cl.uchile.dcc.mobile.foodregistry.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.foodregistry.data.database.FoodRegistry
import cl.uchile.dcc.mobile.foodregistry.data.Indicator
import cl.uchile.dcc.mobile.foodregistry.data.OverviewData
import cl.uchile.dcc.mobile.foodregistry.data.repository.FoodDataRepository
import cl.uchile.dcc.mobile.foodregistry.data.repository.FoodRegistryAppRepository
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryEventState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FoodRegistryViewModel(
    private val configRepo: FoodRegistryAppRepository,
    private val dataRepo: FoodDataRepository,
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {
    val appTheme: StateFlow<String> = configRepo.theme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "Auto"
        )

    fun changeTheme() {
        var theme = appTheme.value
        when (theme) {
            "Auto" -> theme = "Claro"
            "Claro" -> theme = "Oscuro"
            "Oscuro" -> theme = "Claro"
        }
        viewModelScope.launch {
            configRepo.setTheme(theme)
        }
    }

    val username: StateFlow<String> = configRepo.name
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "Anónimo"
        )

    fun setUsername(name: String) {
        viewModelScope.launch {
            configRepo.setName(name)
        }
    }

    // Estados del formulario de registro
    private val _formState = MutableStateFlow(FoodRegistryFormState())
    val formState: StateFlow<FoodRegistryFormState> = _formState

    private fun maskDate(fecha: String): String {
        val fecha = fecha.replace("/", "")
        var day = ""
        var month = ""
        var year = ""

        if (fecha.length <= 2) {
            day = fecha
            return day
        }
        day = fecha.substring(0, 2)
        if (fecha.length <= 4) {
            month = fecha.substring(2)
            return "$day/$month"
        }
        month = fecha.substring(2, 4)
        year = fecha.substring(4)
        return "$day/$month/$year"
    }

    fun setFecha(fecha: String) {
        _formState.update {
            it.copy(
                fecha = maskDate(fecha)
            )
        }
    }

    fun setDescripcion(descripcion: String) {
        _formState.update {
            it.copy(
                descripcion = descripcion
            )
        }
    }

    fun setTipo(tipo: String) {
        _formState.update {
            it.copy(
                tipoId = tipo
            )
        }
    }

    fun setCalorias(calorias: String) {
        _formState.update {
            it.copy(
                calorias = calorias
            )
        }
    }

    fun setCarbohidratos(carbohidratos: String) {
        _formState.update {
            it.copy(
                carbohidratos = carbohidratos
            )
        }
    }

    fun resetFormState() {
        _formState.update {
            FoodRegistryFormState()
        }
    }

    // Eventos del formulario de registro
    private val _eventState = MutableStateFlow(FoodRegistryEventState.Empty)
    val eventState: StateFlow<FoodRegistryEventState> = _eventState

    private fun decodeDate(fecha: String): String {
        val fecha = fecha.split("/")
        return "${fecha[2]}-${fecha[1]}-${fecha[0]}"
    }

    fun recodeDate(fecha: String): String {
        val fecha = fecha.split("-")
        return "${fecha[2]}/${fecha[1]}/${fecha[0]}"
    }

    fun addFoodRegistry() {
        val foodRegistry = FoodRegistry(
            fecha = decodeDate(_formState.value.fecha),
            tipoId = _formState.value.tipoId,
            descripcion = _formState.value.descripcion,
            calorias = _formState.value.calorias?.trim()?.toInt() ?: 0,
            carbohidratos = _formState.value.carbohidratos?.trim()?.toInt() ?: 0
        )
        viewModelScope.launch {
            val id = dataRepo.addFoodRegistry(foodRegistry)
            Log.d("FoodRegistryViewModel", "Added food registry with id: $id")
        }
        // _eventState.value = FoodRegistryEventState.Success
        resetFormState()
    }

    private val _foodRegistryList = MutableStateFlow<List<FoodRegistry>>(emptyList())
    val foodRegistryList: StateFlow<List<FoodRegistry>> = _foodRegistryList

    fun getFoodRegistries() {
        viewModelScope.launch {
            val foodRegistry = dataRepo.getAllFoodRegistry()
            _foodRegistryList.value = foodRegistry
            Log.d("FoodRegistryViewModel", "Got ${foodRegistry.size} food registries")
            Log.d("FoodRegistryViewModel", "Food registries: $foodRegistry")
        }
    }

    fun getOverview(): List<OverviewData> {
        val overviewData = mutableListOf<OverviewData>()
        /*
        foodRegistryList.forEach {
            val fecha = it.fecha
            val indicator = overviewData.find { it.fecha == fecha }
            if (indicator == null) {
                overviewData.add(
                    OverviewData(
                        fecha,
                        Indicator(it.calorias, it.carbohidratos)
                    )
                )
            } else {
                indicator.indicadores.calorias += it.calorias
                indicator.indicadores.carbohidratos += it.carbohidratos
            }
        }
        */
        return overviewData
    }

    // Historial de comidas
    private val _foodRegistryRepository = MutableStateFlow<List<FoodRegistry>>(emptyList())
    val foodRegistryRepository: StateFlow<List<FoodRegistry>> = _foodRegistryRepository

    private val _foodRegistryId = savedStateHandle
        .getStateFlow("foodRegistryId", "")
    val foodRegistryId: StateFlow<String> = _foodRegistryId

    val foodRegistry: StateFlow<FoodRegistry?> = _foodRegistryId.map {
        _foodRegistryRepository.value.find { it.id == _foodRegistryId.value.toInt() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    fun setFoodRegistryId(id: String) {
        savedStateHandle["foodRegistryId"] = id
    }
}