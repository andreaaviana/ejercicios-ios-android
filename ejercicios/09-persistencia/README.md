# 09 · Persistencia: diario de estados de ánimo

## Objetivo
Guardar datos **en el dispositivo** para que sigan ahí al cerrar y volver a abrir la app.

Hay dos niveles de persistencia y vas a practicar los dos:

| Para guardar… | iOS | Android |
|---|---|---|
| Ajustes sencillos (un nombre, un modo, un número) | `UserDefaults` / `@AppStorage` | `SharedPreferences` / DataStore |
| Listas de objetos, datos estructurados | **SwiftData** | **Room** |

## La app
Un diario donde cada día registras cómo te sientes:

```
  Hola, Ana 👋                 [⚙]

  ¿Cómo te sientes hoy?
  😄  🙂  😐  🙁  😢

  [ Nota opcional...          ]
  [ Guardar ]

  ── Historial ──
  😄  10 nov · "Terminé el ejercicio 08"
  😐  09 nov
```

## Pasos

### Parte A — ajuste sencillo
1. Guarda el **nombre del usuario** con `@AppStorage("nombre")` (iOS) o `SharedPreferences` (Android).
2. Muestra "Hola, <nombre> 👋" arriba. Si no hay nombre, pide que lo escriba.
3. **Cierra la app del todo** (no solo minimizar) y vuelve a abrirla. El nombre debe seguir ahí.

### Parte B — lista de registros
4. Modelo `Registro` con `id`, `fecha`, `emoji` y `nota`.
5. Al pulsar *Guardar* se crea un registro con la fecha actual.
6. Historial ordenado del más reciente al más antiguo.
7. Se puede borrar un registro.
8. Cierra y abre la app: el historial sigue ahí.

## Configuración

**iOS (SwiftData, iOS 17+)**: en el archivo `App` principal añade `.modelContainer(for: Registro.self)` al `WindowGroup`.

```swift
@main
struct MiApp: App {
    var body: some Scene {
        WindowGroup { DiarioView() }
            .modelContainer(for: Registro.self)
    }
}
```

**Android (Room)**: Room necesita **KSP** (un generador de código). En `app/build.gradle.kts`:
```kotlin
plugins {
    // ...
    id("com.google.devtools.ksp") version "<la que corresponda a tu versión de Kotlin>"
}
dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
}
```
La versión de KSP debe empezar igual que tu versión de Kotlin (mira `libs.versions.toml` o la [tabla de versiones de KSP](https://github.com/google/ksp/releases)). Este paso suele dar guerra: es normal, y aprender a leer errores de Gradle es parte del ejercicio.

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS:
  ```swift
  @Model final class Registro { var fecha: Date; var emoji: String; var nota: String; init(...) }

  @Environment(\.modelContext) private var context
  @Query(sort: \Registro.fecha, order: .reverse) private var registros: [Registro]

  context.insert(Registro(...))      // guardar
  context.delete(registro)           // borrar
  ```
- Android:
  ```kotlin
  @Entity data class Registro(@PrimaryKey(autoGenerate = true) val id: Long = 0, val fecha: Long, ...)

  @Dao interface RegistroDao {
      @Query("SELECT * FROM Registro ORDER BY fecha DESC") fun todos(): Flow<List<Registro>>
      @Insert suspend fun insertar(r: Registro)
      @Delete suspend fun borrar(r: Registro)
  }
  ```
  Como el `Flow` emite cada vez que cambia la tabla, la lista se actualiza sola.
</details>

## Retos extra
- Estadística: emoji más frecuente de los últimos 7 días.
- Editar un registro existente.
- Ajuste "modo oscuro forzado" guardado en `@AppStorage` / DataStore.
- Android: sustituye `SharedPreferences` por **DataStore** (la opción moderna).
