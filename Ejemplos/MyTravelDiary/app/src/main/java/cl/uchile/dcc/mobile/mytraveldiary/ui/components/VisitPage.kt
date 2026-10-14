package cl.uchile.dcc.mobile.mytraveldiary.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlace
import cl.uchile.dcc.mobile.mytraveldiary.viewmodel.PlacesViewModel
import coil3.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitPage(
    viewModel: PlacesViewModel,
    visitedPlace: VisitedPlace
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { viewModel.selectPlace(null) },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = visitedPlace.nombre, modifier = Modifier.padding(0.dp, 4.dp))
            Text(text = visitedPlace.descripcion, modifier = Modifier.padding(0.dp, 4.dp) )
            Text(text = visitedPlace.fecha, modifier = Modifier.padding(0.dp, 4.dp))
            if (visitedPlace.imagen != null) {
                Image(
                    painter = rememberAsyncImagePainter(visitedPlace.imagen),
                    contentDescription = "Foto de la visita a ${visitedPlace.nombre}",
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(text = "Sin imagen", modifier = Modifier.padding(0.dp, 4.dp))
                Button(onClick = {
                    viewModel.openCamera()
                }) {
                    Text(text = "Tomar Foto")
                }
            }
        }
    }
}