package cl.uchile.dcc.mobile.peoplecounter.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(text: String, callBack: () -> Unit) {
    Button(
        onClick = { callBack() },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        content = {
            Text(
                text = text,
                modifier = Modifier.padding(32.dp)
            )
        }
    )
}