package cl.uchile.dcc.mobile.mytraveldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.dcc.mobile.mytraveldiary.data.VisitedPlaceRepository
import cl.uchile.dcc.mobile.mytraveldiary.ui.MyTravelDiaryApp
import cl.uchile.dcc.mobile.mytraveldiary.ui.theme.MyTravelDiaryTheme
import cl.uchile.dcc.mobile.mytraveldiary.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = VisitedPlaceRepository()
        val viewModel = PlacesViewModel(repository)
        setContent {
            MyTravelDiaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MyTravelDiaryApp(viewModel, Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}
