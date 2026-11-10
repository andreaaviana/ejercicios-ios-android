# 07 · Arquitectura MVVM: "Adivina el número"

## Objetivo
Separar la **lógica** de la **vista** usando un **ViewModel**. Es la arquitectura recomendada tanto por Apple como por Google para apps sencillas.

```
  ┌────────┐   acciones (onIntentar)   ┌────────────┐
  │  Vista │ ────────────────────────▶ │ ViewModel  │  ← lógica y estado
  │        │ ◀──────────────────────── │            │
  └────────┘   estado (mensaje, …)     └────────────┘
                                             │
                                        ┌────────┐
                                        │ Modelo │  ← datos
                                        └────────┘
```

- **Vista**: solo pinta y avisa de lo que hace el usuario. No decide nada.
- **ViewModel**: guarda el estado y contiene las reglas. No sabe nada de botones ni colores.

## El juego
La app piensa un número del **1 al 100**. El usuario escribe un número y pulsa *Probar*. La app responde:
- "📈 Más alto" / "📉 Más bajo"
- "🎉 ¡Correcto! en N intentos" → aparece el botón *Jugar otra vez*.

## Pasos
1. Crea `JuegoViewModel` con:
   - estado: `numeroSecreto` (privado), `intentos`, `mensaje`, `haGanado`
   - función `intentar(_ texto: String)` → valida el texto, incrementa intentos y actualiza el mensaje
   - función `reiniciar()`
2. Crea la vista `JuegoView`/`JuegoScreen`: campo de texto, botón *Probar*, mensaje, contador de intentos y botón *Jugar otra vez* (solo si ha ganado).
3. La vista **no** puede contener `if numero > secreto`… Toda la lógica va en el ViewModel.
4. Haz que el número secreto se pueda **inyectar** desde fuera (parámetro del init/constructor con un valor por defecto aleatorio). Así podrás testearlo.
5. Escribe al menos **3 tests unitarios** del ViewModel (sin interfaz): más alto, más bajo y acierto.

### Dependencia en Android
```kotlin
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
```

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS (iOS 17+):
  ```swift
  @Observable
  final class JuegoViewModel { var mensaje = "" ... }

  struct JuegoView: View {
      @State private var vm = JuegoViewModel()
  }
  ```
- Android:
  ```kotlin
  data class JuegoUiState(val intentos: Int = 0, val mensaje: String = "", val haGanado: Boolean = false)

  class JuegoViewModel : ViewModel() {
      private val _uiState = MutableStateFlow(JuegoUiState())
      val uiState: StateFlow<JuegoUiState> = _uiState.asStateFlow()
      fun intentar(texto: String) { _uiState.update { it.copy(...) } }
  }

  @Composable
  fun JuegoScreen(vm: JuegoViewModel = viewModel()) {
      val estado by vm.uiState.collectAsState()
  }
  ```
- Gira el emulador Android: el juego **no se reinicia** porque el ViewModel sobrevive a la rotación. Compáralo con el contador del ejercicio 03 usando `remember`.
</details>

## Retos extra
- Niveles de dificultad (1–10, 1–100, 1–1000) elegidos con un selector.
- Guarda el récord (menor número de intentos) en memoria y muéstralo.
- Limita a 7 intentos; si se acaban, has perdido y se muestra el número.
