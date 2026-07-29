package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.ui.component.DigitCounter
import cl.uchile.dcc.mobile.peoplecounter.ui.component.SubmitButton

@Composable
fun PeopleCounter(modifier: Modifier = Modifier.Companion) {
    var contador by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var resto = 0
            DigitCounter(contador / 100)
            resto = contador % 100
            DigitCounter(resto / 10)
            resto = resto % 10
            DigitCounter(resto)
        }
        SubmitButton(
            "Contar",
            callBack = {
                if (contador < 999) {
                    contador++
                }
            }
        )
    }
}