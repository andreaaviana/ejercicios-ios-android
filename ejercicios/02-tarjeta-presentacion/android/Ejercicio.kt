// Añade aquí arriba la línea `package` de tu proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TarjetaScreen() {
    Column {
        // TODO 1: icono grande (Icons.Default.AccountCircle)
        // TODO 2: nombre en grande y negrita
        // TODO 3: profesión en gris
        // TODO 4: tres FilaContacto (email, teléfono, web)
        Text("Empieza aquí")
    }
    // TODO 5: que ocupe toda la pantalla y tenga color de fondo (Modifier)
}

@Composable
fun FilaContacto(icono: ImageVector, texto: String) {
    // TODO: Row con Icon(icono, contentDescription = null) y Text(texto)
    Text(texto)
}

@Preview(showBackground = true)
@Composable
fun TarjetaScreenPreview() {
    TarjetaScreen()
}
