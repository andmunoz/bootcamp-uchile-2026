package cl.uchile.dcc.mobile.peoplecounter.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DigitCounter(numero: Int) {
    Card() {
        Text(
            text = numero.toString(),
            modifier = Modifier.padding(40.dp, 32.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}
