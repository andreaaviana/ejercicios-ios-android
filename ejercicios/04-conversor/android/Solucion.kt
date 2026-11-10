// Añade aquí arriba la línea `package` de tu proyecto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun celsiusAFahrenheit(c: Double): Double = c * 9 / 5 + 32

fun fahrenheitACelsius(f: Double): Double = (f - 32) * 5 / 9

enum class Modo(val etiqueta: String) {
    C_A_F("°C → °F"),
    F_A_C("°F → °C")
}

@Composable
fun ConversorScreen() {
    var texto by remember { mutableStateOf("") }
    var modo by remember { mutableStateOf(Modo.C_A_F) }

    // Se recalcula en cada recomposición (cuando cambia texto o modo)
    val valor = texto.replace(',', '.').toDoubleOrNull()
    val resultado = valor?.let {
        when (modo) {
            Modo.C_A_F -> "%.1f °F".format(celsiusAFahrenheit(it))
            Modo.F_A_C -> "%.1f °C".format(fahrenheitACelsius(it))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Conversor de temperatura", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Temperatura") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Modo.entries.forEach { opcion ->
                FilterChip(
                    selected = modo == opcion,
                    onClick = { modo = opcion },
                    label = { Text(opcion.etiqueta) }
                )
            }
        }

        if (resultado != null) {
            Text("Resultado: $resultado", style = MaterialTheme.typography.headlineMedium)
        } else {
            Text("Introduce un número válido", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversorScreenPreview() {
    ConversorScreen()
}
