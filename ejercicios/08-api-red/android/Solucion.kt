// Añade aquí arriba la línea `package` de tu proyecto
// Requiere permiso INTERNET y dependencias Retrofit, Gson, Coil y ViewModel (ver README)

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// ---------- Modelo ----------

data class RespuestaPersonajes(val results: List<Personaje>)

data class Personaje(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)

// ---------- Servicio ----------

interface RickAndMortyApi {
    @GET("character")
    suspend fun personajes(): RespuestaPersonajes
}

object PersonajesService {
    val api: RickAndMortyApi = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RickAndMortyApi::class.java)
}

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
        _estado.value = EstadoPantalla.Cargando
        viewModelScope.launch {
            _estado.value = try {
                EstadoPantalla.Exito(PersonajesService.api.personajes().results)
            } catch (e: Exception) {
                EstadoPantalla.Error("No se pudieron cargar los personajes.\n${e.message}")
            }
        }
    }
}

// ---------- Vista ----------

@Composable
fun PersonajesScreen(vm: PersonajesViewModel = viewModel()) {
    val estado by vm.estado.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val e = estado) {
            is EstadoPantalla.Cargando -> CircularProgressIndicator()

            is EstadoPantalla.Error -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(32.dp)
            ) {
                Text(e.mensaje, textAlign = TextAlign.Center)
                Button(onClick = vm::cargar) { Text("Reintentar") }
            }

            is EstadoPantalla.Exito -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(e.personajes, key = { it.id }) { personaje ->
                    FilaPersonaje(personaje)
                }
            }
        }
    }
}

@Composable
fun FilaPersonaje(personaje: Personaje) {
    val colorEstado = when (personaje.status) {
        "Alive" -> Color(0xFF4CAF50)
        "Dead" -> Color.Red
        else -> Color.Gray
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = personaje.image,
            contentDescription = personaje.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp))
        )
        Column {
            Text(personaje.name, style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(Modifier.size(8.dp).background(colorEstado, CircleShape))
                Text(
                    "${personaje.species} · ${personaje.status}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}
