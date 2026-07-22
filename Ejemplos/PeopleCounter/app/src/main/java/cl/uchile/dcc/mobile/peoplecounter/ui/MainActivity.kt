package cl.uchile.dcc.mobile.peoplecounter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.uchile.dcc.mobile.peoplecounter.ui.theme.PeopleCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Se usé la pantalla del dispositivo completa
        setContent {
            PeopleCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PeopleCounter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PeopleCounter(modifier: Modifier = Modifier.Companion) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "People Counter App",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        RowCounter()
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Count!",
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Composable
fun RowCounter() {
    Row() {
        Card() {
            Text(
                text = "0",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Card() {
            Text(
                text = "0",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Card() {
            Text(
                text = "0",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

/* @Preview(showBackground = true)
@Composable
fun PeopleCounterPreview() {
    PeopleCounterTheme {
        PeopleCounter()
    }
} */