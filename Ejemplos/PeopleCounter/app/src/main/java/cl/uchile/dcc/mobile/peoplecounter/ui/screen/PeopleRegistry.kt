package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
    val personas by remember { mutableStateOf( mutableListOf<PersonRegistry>()) }
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
            ""
        )
        SubmitButton(
            "Registrar",
            callBack = {
                personas.add(PersonRegistry(nombre, "28/07/2026", "13:00"))
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
