// Añade aquí arriba la línea `package` de tu proyecto
// Requiere la dependencia lifecycle-viewmodel-compose (ver README)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class JuegoUiState(
    val intentos: Int = 0,
    val mensaje: String = "Adivina un número del 1 al 100",
    val haGanado: Boolean = false
)

/** `generarNumero` se puede sustituir en los tests para que el número no sea aleatorio. */
class JuegoViewModel(
    private val generarNumero: () -> Int = { Random.nextInt(1, 101) }
) : ViewModel() {

    private var numeroSecreto = generarNumero()

    private val _uiState = MutableStateFlow(JuegoUiState())
    val uiState: StateFlow<JuegoUiState> = _uiState.asStateFlow()

    fun intentar(texto: String) {
        if (_uiState.value.haGanado) return

        val numero = texto.trim().toIntOrNull()
        if (numero == null || numero !in 1..100) {
            _uiState.update { it.copy(mensaje = "⚠️ Escribe un número entre 1 y 100") }
            return
        }

        _uiState.update { estado ->
            val intentos = estado.intentos + 1
            when {
                numero < numeroSecreto -> estado.copy(intentos = intentos, mensaje = "📈 Más alto")
                numero > numeroSecreto -> estado.copy(intentos = intentos, mensaje = "📉 Más bajo")
                else -> estado.copy(
                    intentos = intentos,
                    mensaje = "🎉 ¡Correcto! en $intentos intentos",
                    haGanado = true
                )
            }
        }
    }

    fun reiniciar() {
        numeroSecreto = generarNumero()
        _uiState.value = JuegoUiState()
    }
}

@Composable
fun JuegoScreen(vm: JuegoViewModel = viewModel()) {
    val estado by vm.uiState.collectAsState()
    var texto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        Text("Adivina el número", style = MaterialTheme.typography.headlineLarge)
        Text(estado.mensaje, style = MaterialTheme.typography.titleLarge)
        Text("Intentos: ${estado.intentos}")

        if (estado.haGanado) {
            Button(onClick = {
                vm.reiniciar()
                texto = ""
            }) {
                Text("Jugar otra vez")
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = { Text("1 – 100") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = {
                    vm.intentar(texto)
                    texto = ""
                }) {
                    Text("Probar")
                }
            }
        }
    }
}

// Tests en app/src/test/java/<tu paquete>/JuegoViewModelTest.kt
//
// import org.junit.Assert.assertEquals
// import org.junit.Assert.assertTrue
// import org.junit.Test
//
// class JuegoViewModelTest {
//     @Test
//     fun numeroBajoPideMasAlto() {
//         val vm = JuegoViewModel(generarNumero = { 50 })
//         vm.intentar("10")
//         assertTrue(vm.uiState.value.mensaje.contains("alto"))
//         assertEquals(1, vm.uiState.value.intentos)
//     }
//
//     @Test
//     fun numeroAltoPideMasBajo() {
//         val vm = JuegoViewModel(generarNumero = { 50 })
//         vm.intentar("90")
//         assertTrue(vm.uiState.value.mensaje.contains("bajo"))
//     }
//
//     @Test
//     fun acertarGana() {
//         val vm = JuegoViewModel(generarNumero = { 50 })
//         vm.intentar("10")
//         vm.intentar("50")
//         assertTrue(vm.uiState.value.haGanado)
//         assertEquals(2, vm.uiState.value.intentos)
//     }
//
//     @Test
//     fun textoInvalidoNoCuentaComoIntento() {
//         val vm = JuegoViewModel(generarNumero = { 50 })
//         vm.intentar("hola")
//         assertEquals(0, vm.uiState.value.intentos)
//     }
// }
