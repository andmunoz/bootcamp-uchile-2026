package cl.uchile.dcc.mobile.foodregistry.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.foodregistry.data.FoodRegistry
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryEventState
import cl.uchile.dcc.mobile.foodregistry.ui.screenstates.FoodRegistryFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FoodRegistryViewModel(
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
): ViewModel() {
    // Estados del formulario de registro
    private val _formState = MutableStateFlow(FoodRegistryFormState())
    val formState: StateFlow<FoodRegistryFormState> = _formState

    private fun maskDate(fecha: String): String {
        val fecha = fecha.replace("/", "")
        var day: String = ""
        var month: String = ""
        var year: String = ""

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

    fun addFoodRegistry() {
        val foodRegistry = FoodRegistry(
            fecha = _formState.value.fecha,
            tipoId = _formState.value.tipoId,
            descripcion = _formState.value.descripcion,
            calorias = _formState.value.calorias?.toInt()?:0,
            carbohidratos = _formState.value.carbohidratos?.toInt()?:0
        )
        _foodRegistryRepository.update {
            it + foodRegistry
        }
        resetFormState()
    }

    // Historial de comidas
    val _foodRegistryRepository = MutableStateFlow<List<FoodRegistry>>(emptyList())
    val foodRegistryRepository: StateFlow<List<FoodRegistry>> = _foodRegistryRepository

    private val _foodRegistryId = savedStateHandle
        .getStateFlow("foodRegistryId", "")
    val foodRegistryId: StateFlow<String> = _foodRegistryId

    val foodRegistry: StateFlow<FoodRegistry?> = _foodRegistryId.map {
        _foodRegistryRepository.value.find  { it.id == _foodRegistryId.value }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    fun setFoodRegistryId(id: String) {
        savedStateHandle["foodRegistryId"] = id
    }
}