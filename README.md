# Ejercicios iOS + Android para principiantes

Un recorrido práctico para aprender a desarrollar apps móviles **en las dos plataformas a la vez**:

| | iOS | Android |
|---|---|---|
| IDE | Xcode | Android Studio |
| Lenguaje | Swift | Kotlin |
| UI | SwiftUI | Jetpack Compose |

Cada ejercicio se resuelve **en las dos plataformas**. Así ves que los conceptos (estado, listas, navegación, red…) son los mismos y solo cambia la sintaxis.

## Cómo usar este repo

1. Empieza por [`00-entorno`](ejercicios/00-entorno/README.md) e instala las herramientas.
2. Haz los ejercicios **en orden**: cada uno se apoya en el anterior.
3. En cada carpeta encontrarás:
   ```
   NN-nombre/
   ├── README.md          ← enunciado, pasos, pistas y retos extra
   ├── ios/
   │   ├── Ejercicio.swift   ← código de partida con TODOs
   │   └── Solucion.swift    ← una solución posible
   └── android/
       ├── Ejercicio.kt      ← código de partida con TODOs
       └── Solucion.kt       ← una solución posible
   ```
4. **Intenta resolverlo sin mirar la solución.** Si te atascas más de 20 minutos, lee las pistas. Si sigues atascado, mira la solución, ciérrala y vuelve a escribirlo tú.
5. Marca tu progreso en la tabla de abajo (edita este README).

### Cómo usar el código de los ejercicios con interfaz (02 en adelante)

Los archivos de este repo **no son proyectos completos**: son el código que tienes que llevar a un proyecto nuevo. Así practicas también crear proyectos desde cero.

- **iOS:** crea un proyecto nuevo en Xcode (`iOS → App`, SwiftUI). Copia el contenido de `ios/Ejercicio.swift` en un archivo nuevo del proyecto (⌘N → *Swift File*) y en `ContentView.swift` muestra la vista principal del ejercicio (por ejemplo `TarjetaView()`).
- **Android:** crea un proyecto nuevo en Android Studio (`Empty Activity`). Copia el contenido de `android/Ejercicio.kt` en un archivo nuevo junto a `MainActivity.kt`, **añade arriba la línea `package` de tu proyecto** y dentro de `setContent { ... }` llama a la pantalla del ejercicio (por ejemplo `TarjetaScreen()`).

Puedes usar **un único proyecto por plataforma** para todos los ejercicios, añadiendo un archivo nuevo cada vez.

> Consejo: haz primero la versión de la plataforma que te resulte más cómoda y después "tradúcela" a la otra con ayuda de [`GLOSARIO.md`](GLOSARIO.md).

## Ruta de aprendizaje

| # | Ejercicio | Conceptos | iOS | Android |
|---|---|---|:-:|:-:|
| 00 | [Preparar el entorno](ejercicios/00-entorno/README.md) | Xcode, Android Studio, simulador/emulador | ☐ | ☐ |
| 01 | [Fundamentos del lenguaje](ejercicios/01-fundamentos-lenguaje/README.md) | variables, opcionales/nulos, funciones, colecciones, structs/data classes | ☐ | ☐ |
| 02 | [Tarjeta de presentación](ejercicios/02-tarjeta-presentacion/README.md) | primera pantalla, layouts, imágenes, modificadores | ☐ | ☐ |
| 03 | [Contador](ejercicios/03-contador/README.md) | estado, botones, recomposición | ☐ | ☐ |
| 04 | [Conversor de temperatura](ejercicios/04-conversor/README.md) | campos de texto, validación de entrada | ☐ | ☐ |
| 05 | [Lista de la compra](ejercicios/05-lista-compra/README.md) | listas, añadir/borrar elementos | ☐ | ☐ |
| 06 | [Navegación lista → detalle](ejercicios/06-navegacion/README.md) | navegación entre pantallas, paso de datos | ☐ | ☐ |
| 07 | [Arquitectura MVVM](ejercicios/07-mvvm/README.md) | ViewModel, separar lógica de la vista | ☐ | ☐ |
| 08 | [Consumir una API](ejercicios/08-api-red/README.md) | red, JSON, async/await y corrutinas, estados de carga | ☐ | ☐ |
| 09 | [Persistencia](ejercicios/09-persistencia/README.md) | guardar datos en el dispositivo | ☐ | ☐ |
| 10 | [Proyecto final: app de tareas](ejercicios/10-proyecto-final/README.md) | todo lo anterior junto | ☐ | ☐ |

Tiempo orientativo: 1–2 tardes por ejercicio. El proyecto final, una o dos semanas.

## Recursos recomendados

**iOS**
- [Develop in Swift Tutorials](https://developer.apple.com/tutorials/develop-in-swift) (Apple, gratuito)
- [SwiftUI Tutorials](https://developer.apple.com/tutorials/swiftui)
- [The Swift Programming Language](https://docs.swift.org/swift-book/) (libro oficial)
- [Hacking with Swift – 100 Days of SwiftUI](https://www.hackingwithswift.com/100/swiftui)

**Android**
- [Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course) (Google, gratuito)
- [Kotlin Playground](https://play.kotlinlang.org/) (ejecuta Kotlin en el navegador)
- [Documentación de Jetpack Compose](https://developer.android.com/develop/ui/compose/documentation)
- [Kotlin Koans](https://kotlinlang.org/docs/koans.html)
