// Añade aquí arriba la línea `package` de tu proyecto
// Requiere la dependencia navigation-compose (ver README)

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

data class Pais(
    val id: Int,
    val nombre: String,
    val bandera: String,
    val capital: String,
    val poblacionMillones: Double
)

val paisesEjemplo = listOf(
    Pais(1, "España", "🇪🇸", "Madrid", 48.6),
    Pais(2, "Francia", "🇫🇷", "París", 68.4),
    Pais(3, "Italia", "🇮🇹", "Roma", 58.9),
    Pais(4, "Portugal", "🇵🇹", "Lisboa", 10.6),
    Pais(5, "México", "🇲🇽", "Ciudad de México", 130.0),
    Pais(6, "Argentina", "🇦🇷", "Buenos Aires", 46.0),
)

// Llama a esta función desde setContent { ... }
@Composable
fun PaisesApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            ListaPaisesScreen(onPaisClick = { id -> navController.navigate("detalle/$id") })
        }
        composable("detalle/{id}") { entry ->
            val id = entry.arguments?.getString("id")?.toIntOrNull()
            val pais = paisesEjemplo.firstOrNull { it.id == id }
            if (pais != null) {
                DetallePaisScreen(pais = pais, onVolver = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPaisesScreen(onPaisClick: (Int) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Países") }) }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(paisesEjemplo, key = { it.id }) { pais ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPaisClick(pais.id) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(pais.bandera, fontSize = 32.sp)
                    Spacer(Modifier.width(16.dp))
                    Text(pais.nombre, style = MaterialTheme.typography.titleMedium)
                }
                HorizontalDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePaisScreen(pais: Pais, onVolver: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pais.nombre) },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(pais.bandera, fontSize = 120.sp)
            Text(pais.nombre, style = MaterialTheme.typography.headlineLarge)
            Text("Capital: ${pais.capital}")
            Text("Población: ${"%.1f".format(pais.poblacionMillones)} millones")
        }
    }
}
