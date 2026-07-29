package cl.uchile.dcc.mobile.peoplecounter.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MenuButton(texto: String, callBack: () -> Unit) {
    Button(
        onClick = {
            callBack()
        },
        content = {
            Text(
                text = texto,
                modifier = Modifier.padding(16.dp)
            )
        }
    )
}