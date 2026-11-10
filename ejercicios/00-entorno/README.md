# 00 · Preparar el entorno

## Objetivo
Tener Xcode y Android Studio instalados y ser capaz de ejecutar una app "Hello World" en el simulador de iOS y en el emulador de Android.

## iOS (Xcode)

1. Instala **Xcode** desde la Mac App Store (ocupa bastante; ten paciencia).
2. Ábrelo una vez y acepta instalar los componentes adicionales. Instala también la plataforma **iOS** cuando te lo pida.
3. `File → New → Project… → iOS → App`.
   - Product Name: `HolaMundo`
   - Interface: **SwiftUI**
   - Language: **Swift**
   - Storage: None
4. Arriba, elige un simulador (por ejemplo *iPhone 16*) y pulsa ▶︎ (⌘R).
5. Abre `ContentView.swift`, cambia el texto `"Hello, world!"` por tu nombre y vuelve a ejecutar.
6. Abre el **Canvas** (⌥⌘↩) y comprueba que la previsualización se actualiza sin ejecutar.

## Android (Android Studio)

1. Descarga **Android Studio** desde <https://developer.android.com/studio> e instálalo.
2. En el asistente inicial deja la instalación *Standard* (descarga el SDK y un emulador).
3. `New Project → Phone and Tablet → Empty Activity` (la que usa Compose).
   - Name: `HolaMundo`
   - Language: **Kotlin**
   - Minimum SDK: el que proponga por defecto
   - Build configuration language: **Kotlin DSL**
4. Espera a que termine el *Gradle sync* (barra de abajo).
5. `Device Manager` → crea un dispositivo virtual (por ejemplo *Pixel 8*) si no hay ninguno.
6. Pulsa ▶︎ *Run 'app'*.
7. Abre `MainActivity.kt`, cambia el texto de `Greeting` y vuelve a ejecutar.
8. Usa la vista **Split** para ver el `@Preview`.

## Comprueba que entiendes

Responde con tus palabras (puedes apuntarlo en este archivo):

- ¿Qué archivo es el punto de entrada de la app en iOS? (pista: busca `@main`)
- ¿Y en Android? (pista: busca `MainActivity` y `AndroidManifest.xml`)
- ¿Qué diferencia hay entre *ejecutar en el simulador* y *ver la preview*?
- ¿Dónde se ven los mensajes de `print` (iOS) y `Log.d` / `println` (Android)?

## Reto extra
- Ejecuta la app en tu propio teléfono (iOS: conecta el iPhone y selecciona tu equipo de firma en *Signing & Capabilities*; Android: activa *Opciones de desarrollador → Depuración USB*).
- Cambia el icono o el nombre de la app que se ve en la pantalla de inicio.
