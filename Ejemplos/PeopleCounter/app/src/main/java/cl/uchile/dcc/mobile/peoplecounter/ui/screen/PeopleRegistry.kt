package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.model.PersonRegistry
import cl.uchile.dcc.mobile.peoplecounter.ui.component.InputTextField
import cl.uchile.dcc.mobile.peoplecounter.ui.component.PeopleCard
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton

@Composable
fun PeopleRegistry(modifier: Modifier = Modifier.Companion) {
    var nombre by remember { mutableStateOf("") }
    val personas: MutableList<PersonRegistry> = mutableListOf()
    personas.add(PersonRegistry("Pedro", "28/07/2026", "12:00"))
    personas.add(PersonRegistry("Juan", "28/07/2026", "12:07"))
    personas.add(PersonRegistry("Diego", "28/07/2026", "12:15"))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        InputTextField(
            "Ingrese el nombre del asistente",
            value = nombre,
            callBack = {
                nombre = it
            }
        )
        SubmitButton(
            "Registrar",
            callBack = {
                val date = java.util.Date()
                val fecha = date.toString().substring(0, 10)
                val hora = date.toString().substring(11, 16)
                personas.add(PersonRegistry(nombre, fecha, hora))
                nombre = ""
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            content = {
                items(personas) { persona ->
                    PeopleCard(persona)
                }
            }
        )
    }
}
