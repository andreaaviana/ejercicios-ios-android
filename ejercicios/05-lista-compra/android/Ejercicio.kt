// Añade aquí arriba la línea `package` de tu proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// TODO 1: data class Articulo(id, nombre, comprado)

@Composable
fun ListaCompraScreen() {
    // TODO 2: estado con la lista (mutableStateListOf)
    var nuevoNombre by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row {
            OutlinedTextField(
                value = nuevoNombre,
                onValueChange = { nuevoNombre = it },
                placeholder = { Text("Escribe un producto...") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                // TODO 3: añadir (sin vacíos) y limpiar el campo
            }) {
                Text("Añadir")
            }
        }

        // TODO 4: LazyColumn con los artículos (tocar = alternar comprado, tachado si comprado)
        // TODO 5: icono de papelera para borrar
        // TODO 7: mensaje si la lista está vacía

        // TODO 6: "N pendientes"
    }
}

@Preview(showBackground = true)
@Composable
fun ListaCompraScreenPreview() {
    ListaCompraScreen()
}
