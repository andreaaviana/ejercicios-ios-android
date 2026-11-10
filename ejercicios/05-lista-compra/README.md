# 05 · Lista de la compra

## Objetivo
Mostrar una **lista dinámica**: añadir elementos, marcarlos como comprados y borrarlos.

## Resultado esperado

```
  Lista de la compra

  [ Escribe un producto... ] [Añadir]

  ☐ Leche
  ☑ Pan          (tachado)
  ☐ Huevos
                         ← desliza para borrar
  2 pendientes
```

## Pasos
1. Crea el modelo `Articulo` con `id`, `nombre` y `comprado`.
   - iOS: `struct Articulo: Identifiable` con `let id = UUID()`.
   - Android: `data class Articulo(val id: Long, val nombre: String, val comprado: Boolean = false)`.
2. Guarda la lista en estado.
3. Campo de texto + botón **Añadir**. No se pueden añadir textos vacíos (ni solo espacios). Tras añadir, vacía el campo.
4. Pinta la lista. Cada fila: casilla + nombre. Al pulsar la fila se alterna `comprado`; si está comprado, el texto sale tachado y en gris.
5. Borrar un elemento: iOS con *swipe to delete* (`.onDelete`); Android con un icono de papelera en la fila.
6. Abajo, un contador: `N pendientes`.
7. Si la lista está vacía, muestra un mensaje tipo *"Tu lista está vacía 🛒"*.

## Concepto clave: ¿por qué un `id`?
Las listas necesitan saber **qué elemento es cuál** para animar y actualizar solo lo que cambia. Si usas el índice o el nombre como identificador, ¿qué pasa al tener dos "Leche"? Pruébalo.

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS:
  - `List { ForEach(articulos) { ... }.onDelete { articulos.remove(atOffsets: $0) } }`
  - Para modificar un elemento: `if let i = articulos.firstIndex(where: { $0.id == a.id }) { articulos[i].comprado.toggle() }`
  - O más corto: `ForEach($articulos) { $articulo in Toggle(articulo.nombre, isOn: $articulo.comprado) }`
  - Tachado: `.strikethrough(articulo.comprado)`
- Android:
  - Para que Compose detecte cambios en la lista usa `remember { mutableStateListOf<Articulo>() }`.
  - `LazyColumn { items(articulos, key = { it.id }) { articulo -> ... } }`
  - Modificar: `articulos[i] = articulos[i].copy(comprado = !articulos[i].comprado)` (las data classes son inmutables, se reemplazan).
  - Tachado: `textDecoration = TextDecoration.LineThrough`
</details>

## Retos extra
- Botón "Borrar comprados".
- Muestra primero los pendientes y después los comprados.
- Añade cantidad a cada artículo con un `Stepper` / botones `+ −`.
- Android: implementa el borrado deslizando con `SwipeToDismissBox`.
