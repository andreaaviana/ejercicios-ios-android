// Añade aquí arriba la línea `package` de tu proyecto
// Requiere Room + KSP y lifecycle-viewmodel-compose (ver README)

import android.app.Application
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ---------- Room ----------

@Entity
data class Registro(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fecha: Long = System.currentTimeMillis(),
    val emoji: String,
    val nota: String = ""
)

@Dao
interface RegistroDao {
    @Query("SELECT * FROM Registro ORDER BY fecha DESC")
    fun todos(): Flow<List<Registro>>

    @Insert
    suspend fun insertar(registro: Registro)

    @Delete
    suspend fun borrar(registro: Registro)
}

@Database(entities = [Registro::class], version = 1)
abstract class DiarioDatabase : RoomDatabase() {
    abstract fun registroDao(): RegistroDao

    companion object {
        @Volatile private var instancia: DiarioDatabase? = null

        fun obtener(context: Context): DiarioDatabase =
            instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(context, DiarioDatabase::class.java, "diario.db")
                    .build()
                    .also { instancia = it }
            }
    }
}

// ---------- ViewModel ----------

class DiarioViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = app.getSharedPreferences("ajustes", Context.MODE_PRIVATE)
    private val dao = DiarioDatabase.obtener(app).registroDao()

    private val _nombre = MutableStateFlow(prefs.getString("nombre", "") ?: "")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    val registros: StateFlow<List<Registro>> = dao.todos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun guardarNombre(nuevo: String) {
        prefs.edit().putString("nombre", nuevo).apply()
        _nombre.value = nuevo
    }

    fun guardar(emoji: String, nota: String) {
        viewModelScope.launch { dao.insertar(Registro(emoji = emoji, nota = nota.trim())) }
    }

    fun borrar(registro: Registro) {
        viewModelScope.launch { dao.borrar(registro) }
    }
}

// ---------- Vista ----------

private val emojis = listOf("😄", "🙂", "😐", "🙁", "😢")
private val formatoFecha = SimpleDateFormat("d MMM · HH:mm", Locale("es", "ES"))

@Composable
fun DiarioScreen(vm: DiarioViewModel = viewModel()) {
    val nombre by vm.nombre.collectAsState()
    val registros by vm.registros.collectAsState()

    var nombreTemporal by remember { mutableStateOf("") }
    var emojiSeleccionado by remember { mutableStateOf("🙂") }
    var nota by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (nombre.isEmpty()) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = nombreTemporal,
                    onValueChange = { nombreTemporal = it },
                    label = { Text("¿Cómo te llamas?") },
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { vm.guardarNombre(nombreTemporal.trim()) }, enabled = nombreTemporal.isNotBlank()) {
                    Text("OK")
                }
            }
        } else {
            Text("Hola, $nombre 👋", style = MaterialTheme.typography.headlineSmall)
        }

        Text("¿Cómo te sientes hoy?", style = MaterialTheme.typography.titleMedium)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            emojis.forEach { emoji ->
                Text(
                    emoji,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .alpha(if (emoji == emojiSeleccionado) 1f else 0.3f)
                        .clickable { emojiSeleccionado = emoji }
                )
            }
        }
        OutlinedTextField(
            value = nota,
            onValueChange = { nota = it },
            placeholder = { Text("Nota opcional...") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            vm.guardar(emojiSeleccionado, nota)
            nota = ""
        }) {
            Text("Guardar")
        }

        Text("Historial", style = MaterialTheme.typography.titleMedium)
        if (registros.isEmpty()) {
            Text("Aún no hay registros", color = Color.Gray)
        }
        LazyColumn {
            items(registros, key = { it.id }) { registro ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(registro.emoji, fontSize = 28.sp)
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(formatoFecha.format(Date(registro.fecha)))
                        if (registro.nota.isNotEmpty()) Text(registro.nota, color = Color.Gray)
                    }
                    IconButton(onClick = { vm.borrar(registro) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Borrar")
                    }
                }
                HorizontalDivider()
            }
        }
    }
}
