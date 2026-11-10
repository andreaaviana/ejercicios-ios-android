# Glosario Swift ↔ Kotlin / SwiftUI ↔ Compose

Chuleta para "traducir" de una plataforma a otra.

## Lenguaje

| Concepto | Swift | Kotlin |
|---|---|---|
| Constante | `let nombre = "Ana"` | `val nombre = "Ana"` |
| Variable | `var edad = 30` | `var edad = 30` |
| Tipo explícito | `let x: Int = 5` | `val x: Int = 5` |
| Opcional / nulable | `var email: String?` | `var email: String?` |
| Desempaquetar seguro | `if let email { ... }` | `email?.let { ... }` |
| Valor por defecto | `email ?? "sin email"` | `email ?: "sin email"` |
| Interpolación | `"Hola \(nombre)"` | `"Hola $nombre"` / `"${user.nombre}"` |
| Función | `func sumar(_ a: Int, _ b: Int) -> Int` | `fun sumar(a: Int, b: Int): Int` |
| Lambda / closure | `{ x in x * 2 }` | `{ x -> x * 2 }` o `{ it * 2 }` |
| Array / lista | `var nums = [1, 2, 3]` | `val nums = mutableListOf(1, 2, 3)` |
| Diccionario / mapa | `["a": 1]` | `mapOf("a" to 1)` |
| Recorrer | `for n in nums { }` | `for (n in nums) { }` |
| Rango | `0..<10` / `0...10` | `0 until 10` / `0..10` |
| Switch | `switch x { case 1: ... default: ... }` | `when (x) { 1 -> ... else -> ... }` |
| Modelo de datos | `struct Persona { let nombre: String }` | `data class Persona(val nombre: String)` |
| Enum | `enum Color { case rojo, azul }` | `enum class Color { ROJO, AZUL }` |
| Protocolo / interfaz | `protocol Volador { func volar() }` | `interface Volador { fun volar() }` |
| Asíncrono | `async` / `await` | `suspend` / corrutinas |
| Imprimir en consola | `print("hola")` | `println("hola")` / `Log.d("TAG", "hola")` |

## Interfaz de usuario

| Concepto | SwiftUI | Jetpack Compose |
|---|---|---|
| Componente de pantalla | `struct MiVista: View { var body: some View { ... } }` | `@Composable fun MiVista() { ... }` |
| Texto | `Text("Hola")` | `Text("Hola")` |
| Botón | `Button("OK") { ... }` | `Button(onClick = { ... }) { Text("OK") }` |
| Apilar vertical | `VStack { }` | `Column { }` |
| Apilar horizontal | `HStack { }` | `Row { }` |
| Superponer | `ZStack { }` | `Box { }` |
| Espaciador | `Spacer()` | `Spacer(Modifier.weight(1f))` |
| Imagen | `Image("foto")` / `Image(systemName: "star")` | `Image(painterResource(R.drawable.foto), null)` / `Icon(Icons.Default.Star, null)` |
| Campo de texto | `TextField("Nombre", text: $nombre)` | `TextField(value = nombre, onValueChange = { nombre = it })` |
| Lista | `List(items) { item in ... }` | `LazyColumn { items(lista) { item -> ... } }` |
| Estilo | modificadores: `.padding().foregroundStyle(.red)` | `Modifier.padding(16.dp)` + parámetros |
| Estado local | `@State var contador = 0` | `var contador by remember { mutableStateOf(0) }` |
| Pasar estado editable a un hijo | `@Binding` | pasar valor + lambda `onChange` |
| ViewModel | `@Observable class VM` + `@State var vm = VM()` | `class VM : ViewModel()` + `viewModel()` |
| Navegación | `NavigationStack` + `NavigationLink` | `NavHost` + `navController.navigate(...)` |
| Previsualizar | `#Preview { MiVista() }` | `@Preview @Composable fun MiVistaPreview() { ... }` |
| Al aparecer | `.task { }` / `.onAppear { }` | `LaunchedEffect(Unit) { }` |

## Herramientas

| | Xcode | Android Studio |
|---|---|---|
| Ejecutar | ⌘R | ⌃R (Mac) / Shift+F10 |
| Parar | ⌘. | ⌘F2 |
| Compilar | ⌘B | ⌘F9 |
| Buscar en todo el proyecto | ⇧⌘F | ⇧⌘F |
| Abrir archivo rápido | ⇧⌘O | ⇧⇧ (doble Shift) |
| Ver previsualización | Canvas (⌥⌘↩) | Split / Design |
| Dispositivo virtual | Simulador | Emulador (Device Manager) |
| Dependencias | Swift Package Manager | Gradle (`build.gradle.kts`) |
| Recursos de imagen | `Assets.xcassets` | `res/drawable` |
