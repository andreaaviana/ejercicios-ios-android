// Añade aquí arriba la línea `package` de tu proyecto
// Requiere permiso INTERNET y dependencias Retrofit, Gson, Coil y ViewModel (ver README)

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ---------- Modelo ----------

// TODO 1: modelos que encajen con el JSON (Gson usa los nombres de las propiedades)
data class RespuestaPersonajes(val results: List<Personaje>)

data class Personaje(
    val id: Int
    // TODO: name, status, species, image
)

// ---------- Servicio ----------

// TODO 2: interfaz Retrofit con @GET("character") suspend fun personajes(): RespuestaPersonajes
// y un objeto que construya Retrofit con baseUrl "https://rickandmortyapi.com/api/"

// ---------- ViewModel ----------

sealed interface EstadoPantalla {
    data object Cargando : EstadoPantalla
    data class Error(val mensaje: String) : EstadoPantalla
    data class Exito(val personajes: List<Personaje>) : EstadoPantalla
}

class PersonajesViewModel : ViewModel() {
    private val _estado = MutableStateFlow<EstadoPantalla>(EstadoPantalla.Cargando)
    val estado: StateFlow<EstadoPantalla> = _estado.asStateFlow()

    init {
        cargar()
    }

    fun cargar() {
        // TODO 3 y 4: viewModelScope.launch { try { ... } catch (e: Exception) { ... } }
    }
}

// ---------- Vista ----------

@Composable
fun PersonajesScreen(vm: PersonajesViewModel = viewModel()) {
    val estado by vm.estado.collectAsState()

    // TODO 5: when (estado) { is EstadoPantalla.Cargando -> CircularProgressIndicator() ... }
    Text("Empieza aquí")
}

@Composable
fun FilaPersonaje(personaje: Personaje) {
    // TODO 6: AsyncImage (Coil) + nombre + especie + círculo de color según status
}
