// Añade aquí arriba la línea `package` de tu proyecto
// Requiere la dependencia lifecycle-viewmodel-compose (ver README)

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class JuegoUiState(
    val intentos: Int = 0,
    val mensaje: String = "Adivina un número del 1 al 100",
    val haGanado: Boolean = false
)

// TODO 4: permite inyectar cómo se genera el número secreto
class JuegoViewModel : ViewModel() {
    // TODO 1: número secreto privado

    private val _uiState = MutableStateFlow(JuegoUiState())
    val uiState: StateFlow<JuegoUiState> = _uiState.asStateFlow()

    fun intentar(texto: String) {
        // TODO 1: valida el texto, cuenta el intento y actualiza el estado con _uiState.update { it.copy(...) }
    }

    fun reiniciar() {
        // TODO 1
    }
}

@Composable
fun JuegoScreen(vm: JuegoViewModel = viewModel()) {
    val estado by vm.uiState.collectAsState()
    var texto by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // TODO 2: campo, botón Probar, mensaje, intentos y botón Jugar otra vez
        // TODO 3: ¡nada de lógica aquí! solo llamar a vm.intentar(...) y vm.reiniciar()
        Text(estado.mensaje)
    }
}

// TODO 5: tests en app/src/test/java/.../JuegoViewModelTest.kt
//
// class JuegoViewModelTest {
//     @Test
//     fun numeroBajoPideMasAlto() {
//         val vm = JuegoViewModel(generarNumero = { 50 })
//         vm.intentar("10")
//         assertTrue(vm.uiState.value.mensaje.contains("alto"))
//         assertEquals(1, vm.uiState.value.intentos)
//     }
// }
