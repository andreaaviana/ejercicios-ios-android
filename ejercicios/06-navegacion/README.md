# 06 · Navegación lista → detalle

## Objetivo
Pasar de una pantalla a otra **llevando datos**: una lista de países y, al tocar uno, una pantalla de detalle.

## Resultado esperado

```
 Pantalla 1: Países              Pantalla 2: Detalle
 ┌──────────────────────┐        ┌──────────────────────┐
 │ 🇪🇸 España         > │  ───▶  │ < Países             │
 │ 🇫🇷 Francia        > │        │        🇪🇸           │
 │ 🇮🇹 Italia         > │        │       España         │
 │ 🇵🇹 Portugal       > │        │ Capital: Madrid      │
 └──────────────────────┘        │ Población: 48 M      │
                                 └──────────────────────┘
```

## Pasos
1. Modelo `Pais` con `id`, `nombre`, `bandera` (emoji), `capital` y `poblacionMillones`. Usa los datos de ejemplo del archivo de partida.
2. Pantalla de lista con todos los países. Cada fila: bandera + nombre.
3. Al tocar una fila, navega a la pantalla de detalle de ese país.
4. La pantalla de detalle muestra todos los datos y tiene un botón para volver (en iOS sale solo; en Android añade una `TopAppBar` con flecha atrás).
5. El título de la barra superior cambia según la pantalla.

## Diferencia importante entre plataformas
- **iOS (`NavigationStack`)**: puedes pasar el objeto `Pais` entero a la vista de destino.
- **Android (Navigation Compose)**: la navegación funciona con **rutas** (como URLs). Lo recomendado es pasar solo el **id** (`"detalle/3"`) y que la pantalla de detalle busque el país. Así, si Android recrea la pantalla, puede reconstruirla.

### Dependencia en Android
Añade Navigation Compose al `app/build.gradle.kts` (usa la última versión estable que te sugiera Android Studio):
```kotlin
dependencies {
    implementation("androidx.navigation:navigation-compose:2.8.5")
}
```
y pulsa *Sync Now*.

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS:
  ```swift
  NavigationStack {
      List(paises) { pais in
          NavigationLink(value: pais) { Text(pais.nombre) }
      }
      .navigationDestination(for: Pais.self) { pais in DetallePaisView(pais: pais) }
  }
  ```
  Para eso `Pais` debe ser `Hashable`.
- Android:
  ```kotlin
  val navController = rememberNavController()
  NavHost(navController, startDestination = "lista") {
      composable("lista") { ListaPaisesScreen(onPaisClick = { id -> navController.navigate("detalle/$id") }) }
      composable("detalle/{id}") { entry -> val id = entry.arguments?.getString("id")?.toIntOrNull() ... }
  }
  ```
  Volver: `navController.popBackStack()`.
</details>

## Retos extra
- Botón "Favorito" en el detalle. ¿Cómo haces que la lista muestre un ❤️ en los favoritos? (pista: el estado tiene que vivir **por encima** de las dos pantallas).
- Añade un buscador que filtre la lista por nombre (iOS: `.searchable`; Android: un `TextField` arriba).
- Añade una tercera pantalla "Acerca de" accesible desde un botón en la barra superior.
