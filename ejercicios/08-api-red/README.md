# 08 · Consumir una API

## Objetivo
Descargar datos de Internet, convertir el **JSON** en modelos y mostrar los tres estados típicos de cualquier pantalla que carga datos: **cargando**, **error** y **éxito**.

Usaremos la API pública y gratuita de Rick and Morty (no necesita registro):

```
GET https://rickandmortyapi.com/api/character
```

Respuesta (resumida):
```json
{
  "info": { "count": 826, "pages": 42, "next": "https://...?page=2" },
  "results": [
    { "id": 1, "name": "Rick Sanchez", "status": "Alive", "species": "Human",
      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg" }
  ]
}
```

Ábrela primero en el navegador para ver el JSON real.

## Resultado esperado

```
  Personajes
  ┌──────────────────────────────┐
  │ [img] Rick Sanchez           │
  │       Human · 🟢 Alive        │
  ├──────────────────────────────┤
  │ [img] Morty Smith            │
  │       Human · 🟢 Alive        │
  └──────────────────────────────┘
```

## Pasos
1. Crea los modelos que representan la respuesta (`RespuestaPersonajes`, `Personaje`). Solo los campos que vas a usar.
2. Crea un servicio `PersonajesService` con una función asíncrona `obtenerPersonajes()`.
3. En el ViewModel, modela el estado de la pantalla con **tres casos**: cargando, error(mensaje), exito(lista).
4. Al aparecer la pantalla, carga los datos.
5. La vista pinta un indicador de carga, un mensaje de error con botón **Reintentar**, o la lista.
6. Cada fila muestra la imagen descargada de Internet, el nombre, la especie y un círculo de color según `status` (verde *Alive*, rojo *Dead*, gris *unknown*).
7. Prueba el caso de error: activa el **modo avión** en el simulador/emulador o cambia la URL.

## Configuración

**iOS**: no hace falta nada. Usarás `URLSession`, `Codable` y `AsyncImage`, que vienen incluidos.

**Android**:
1. En `AndroidManifest.xml`, dentro de `<manifest>`:
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   ```
2. En `app/build.gradle.kts`:
   ```kotlin
   implementation("com.squareup.retrofit2:retrofit:2.11.0")
   implementation("com.squareup.retrofit2:converter-gson:2.11.0")
   implementation("io.coil-kt:coil-compose:2.7.0")              // imágenes
   implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
   ```

## Conceptos clave
| | iOS | Android |
|---|---|---|
| Función asíncrona | `func f() async throws` | `suspend fun f()` |
| Llamarla | `try await f()` dentro de `Task { }` o `.task { }` | dentro de `viewModelScope.launch { }` |
| JSON → modelo | `Codable` + `JSONDecoder` | Retrofit + Gson |
| Imagen remota | `AsyncImage(url:)` | `AsyncImage(model = url)` (Coil) |
| Estados | `enum Estado { case cargando, error(String), exito([Personaje]) }` | `sealed interface Estado` |

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS:
  ```swift
  let (data, _) = try await URLSession.shared.data(from: url)
  let respuesta = try JSONDecoder().decode(RespuestaPersonajes.self, from: data)
  ```
  En la vista: `.task { await vm.cargar() }`.
- Android:
  ```kotlin
  interface RickAndMortyApi {
      @GET("character") suspend fun personajes(): RespuestaPersonajes
  }
  val api = Retrofit.Builder()
      .baseUrl("https://rickandmortyapi.com/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(RickAndMortyApi::class.java)
  ```
  En la vista no hace falta `LaunchedEffect` si llamas a `cargar()` en el `init` del ViewModel.
</details>

## Retos extra
- **Pull to refresh** (`.refreshable` / `PullToRefreshBox`).
- **Paginación**: al llegar al final, carga `info.next`.
- Pantalla de detalle al tocar un personaje (reutiliza lo del ejercicio 06).
- Buscador: `https://rickandmortyapi.com/api/character/?name=rick`.
