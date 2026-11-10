// Añade aquí arriba la línea `package` de tu proyecto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Articulo(
    val id: Long = System.nanoTime(),
    val nombre: String,
    val comprado: Boolean = false
)

@Composable
fun ListaCompraScreen() {
    val articulos = remember {
        mutableStateListOf(
            Articulo(id = 1, nombre = "Leche"),
            Articulo(id = 2, nombre = "Pan", comprado = true)
        )
    }
    var nuevoNombre by remember { mutableStateOf("") }
    val pendientes = articulos.count { !it.comprado }

    fun anadir() {
        val nombre = nuevoNombre.trim()
        if (nombre.isEmpty()) return
        articulos.add(Articulo(nombre = nombre))
        nuevoNombre = ""
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = nuevoNombre,
                onValueChange = { nuevoNombre = it },
                placeholder = { Text("Escribe un producto...") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { anadir() }, enabled = nuevoNombre.isNotBlank()) {
                Text("Añadir")
            }
        }

        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            if (articulos.isEmpty()) {
                Text("Tu lista está vacía 🛒", modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    items(articulos, key = { it.id }) { articulo ->
                        FilaArticulo(
                            articulo = articulo,
                            onToggle = {
                                val i = articulos.indexOf(articulo)
                                articulos[i] = articulo.copy(comprado = !articulo.comprado)
                            },
                            onBorrar = { articulos.remove(articulo) }
                        )
                    }
                }
            }
        }

        Text("$pendientes pendientes", color = Color.Gray)
    }
}

@Composable
fun FilaArticulo(articulo: Articulo, onToggle: () -> Unit, onBorrar: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onToggle),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = articulo.comprado, onCheckedChange = { onToggle() })
        Text(
            text = articulo.nombre,
            modifier = Modifier.weight(1f),
            textDecoration = if (articulo.comprado) TextDecoration.LineThrough else null,
            color = if (articulo.comprado) Color.Gray else Color.Unspecified
        )
        IconButton(onClick = onBorrar) {
            Icon(Icons.Default.Delete, contentDescription = "Borrar ${articulo.nombre}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaCompraScreenPreview() {
    ListaCompraScreen()
}
