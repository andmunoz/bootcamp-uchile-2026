package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
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

@Composable
fun NoteCreateForm(
    onSaveNote: (title: String, content: String, picturePath: String?) -> Unit
) {
    var title by remember { mutableStateOf("")}
    var content by remember { mutableStateOf("")}
    var withPicture by remember { mutableStateOf(true) }

    val cameraHandler = rememberCameraHandler()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 0.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {
                Text("Título de la nota")
            },
            placeholder = {
                Text("Ej: Lista de compras")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = {
                Text("Contenido de la nota")
            },
            placeholder = {
                Text("Escribe aquí los detalles...")
            },
            minLines = 5,
            maxLines = 10,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Checkbox(
                checked = withPicture,
                onCheckedChange = { withPicture = it }
            )
            Text(
                text = "Incluir fotografía"
            )
        }

        Button(
           onClick = {
               if (withPicture) {
                   cameraHandler.takePicture(
                       onSuccess = { path ->
                           onSaveNote(title, content, path)
                       },
                       onError = { error ->
                           onSaveNote(title, content, null)
                       }
                   )
               } else {
                   onSaveNote(title, content, null)
               }
           }
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
            Text("Guardar")
        }
    }
}