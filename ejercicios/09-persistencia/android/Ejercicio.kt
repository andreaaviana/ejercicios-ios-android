// Añade aquí arriba la línea `package` de tu proyecto
// Requiere Room + KSP y lifecycle-viewmodel-compose (ver README)

import android.app.Application
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// ---------- Room ----------

// TODO 4: @Entity data class Registro(@PrimaryKey(autoGenerate = true) val id: Long = 0, fecha, emoji, nota)

// TODO 6 y 7: @Dao interface RegistroDao con todos(): Flow<List<Registro>>, insertar y borrar

// TODO: @Database(entities = [Registro::class], version = 1) abstract class DiarioDatabase : RoomDatabase()
//       con un companion object que devuelva una única instancia (Room.databaseBuilder)

// ---------- ViewModel ----------

// AndroidViewModel nos da acceso a `application` para abrir la base de datos y las preferencias
class DiarioViewModel(app: Application) : AndroidViewModel(app) {
    // TODO 1: lee y guarda el nombre con app.getSharedPreferences("ajustes", Context.MODE_PRIVATE)
    // TODO 6: expón registros como StateFlow (dao.todos().stateIn(...))
    // TODO 5: fun guardar(emoji: String, nota: String)
    // TODO 7: fun borrar(registro: Registro)
}

// ---------- Vista ----------

@Composable
fun DiarioScreen(vm: DiarioViewModel = viewModel()) {
    // TODO 2: saludo o campo para el nombre
    // TODO: selector de emojis, nota y botón Guardar
    // TODO: historial (LazyColumn) con botón de borrar
    Text("Empieza aquí")
}
