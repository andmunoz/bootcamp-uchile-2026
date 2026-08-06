package cl.uchile.dcc.mobile.peoplecounter.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.model.PersonRegistry

@Composable
fun PeopleCard(persona: PersonRegistry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        {
            Row() {
                Text(
                    text = persona.nombre,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = persona.edad.toString() + " años",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = persona.genero,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = (persona.fecha + " " + persona.hora),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}