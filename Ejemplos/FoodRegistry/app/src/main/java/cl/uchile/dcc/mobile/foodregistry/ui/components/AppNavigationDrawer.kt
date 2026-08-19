package cl.uchile.dcc.mobile.foodregistry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.uchile.dcc.mobile.foodregistry.ui.screens.ScreenRoutes
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel

@Composable
fun FoodRegistryNavigationDrawer(
    viewModel: FoodRegistryViewModel,
    navController: NavController,
    onCloseDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = "Mi Alimentación",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        val items = listOf(
            ScreenRoutes.FOODS,
            ScreenRoutes.SETTINGS
        )

        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.title
                    )
                },
                selected = navController.currentBackStackEntry?.destination?.route == item.route,
                onClick = {
                    navController.navigate(item.route)
                    onCloseDrawer()
                },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}