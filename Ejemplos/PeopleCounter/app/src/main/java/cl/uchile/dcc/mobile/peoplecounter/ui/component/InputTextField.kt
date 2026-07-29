package cl.uchile.dcc.mobile.peoplecounter.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputTextField(label: String, initValue: String = "") {
    var valor by remember { mutableStateOf(initValue) }
    OutlinedTextField(
        value = valor,
        onValueChange = { it ->
            valor = it
        },
        label = {
            Text(text = label)
        },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}