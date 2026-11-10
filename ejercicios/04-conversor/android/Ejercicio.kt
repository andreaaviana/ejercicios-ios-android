// Añade aquí arriba la línea `package` de tu proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// TODO 5: implementa la lógica fuera de la vista
fun celsiusAFahrenheit(c: Double): Double = 0.0

fun fahrenheitACelsius(f: Double): Double = 0.0

enum class Modo(val etiqueta: String) {
    C_A_F("°C → °F"),
    F_A_C("°F → °C")
}

@Composable
fun ConversorScreen() {
    var texto by remember { mutableStateOf("") }
    var modo by remember { mutableStateOf(Modo.C_A_F) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // TODO 1: OutlinedTextField con teclado numérico
        // TODO 2: selector entre los dos modos (FilterChip o SegmentedButton)
        // TODO 3 y 4: resultado con un decimal, o mensaje de error en rojo
        Text("Empieza aquí")
    }
}

@Preview(showBackground = true)
@Composable
fun ConversorScreenPreview() {
    ConversorScreen()
}
