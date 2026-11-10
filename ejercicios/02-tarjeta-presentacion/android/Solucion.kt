// Añade aquí arriba la línea `package` de tu proyecto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TarjetaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3F51B5)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Foto de perfil",
            tint = Color.White,
            modifier = Modifier.size(120.dp)
        )
        Text("Ana García", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text("Desarrolladora móvil", fontSize = 18.sp, color = Color.White.copy(alpha = 0.8f))

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilaContacto(Icons.Default.Email, "ana@ejemplo.com")
            FilaContacto(Icons.Default.Phone, "+34 600 000 000")
            FilaContacto(Icons.Default.Home, "github.com/ana")
        }
    }
}

@Composable
fun FilaContacto(icono: ImageVector, texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icono, contentDescription = null, tint = Color.White)
        Spacer(Modifier.width(12.dp))
        Text(texto, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaScreenPreview() {
    TarjetaScreen()
}
