// Añade aquí arriba la línea `package` de tu proyecto
// Requiere la dependencia navigation-compose (ver README)

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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
    // TODO 3: rememberNavController() + NavHost con dos rutas: "lista" y "detalle/{id}"
    Text("Empieza aquí")
}

@Composable
fun ListaPaisesScreen(onPaisClick: (Int) -> Unit) {
    // TODO 2: LazyColumn con los países; al tocar llama a onPaisClick(pais.id)
}

@Composable
fun DetallePaisScreen(pais: Pais, onVolver: () -> Unit) {
    // TODO 4 y 5: Scaffold con TopAppBar (título + flecha atrás) y los datos del país
}
