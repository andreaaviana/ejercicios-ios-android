# 01 · Fundamentos del lenguaje (Swift y Kotlin)

## Objetivo
Practicar lo básico de cada lenguaje **sin interfaz gráfica**: variables, opcionales/nulos, funciones, colecciones, `struct`/`data class` y `enum`.

## Cómo ejecutarlo

**Swift** — desde la terminal:
```bash
cd ejercicios/01-fundamentos-lenguaje/ios
swift Ejercicio.swift
```
(o crea un *Playground* en Xcode: `File → New → Playground` y pega el código)

**Kotlin** — pega `android/Ejercicio.kt` en <https://play.kotlinlang.org/> y pulsa *Run*.
(o en Android Studio: clic derecho en el archivo → *Run 'EjercicioKt'*)

## Enunciado

El archivo de partida tiene varias funciones marcadas con `TODO`. Complétalas hasta que **todas las comprobaciones** del final imprima `✅`.

1. **`saludar(nombre)`** → devuelve `"Hola, <nombre>!"`.
2. **`esPar(n)`** → `true` si el número es par.
3. **`sumaLista(numeros)`** → suma todos los elementos de una lista.
4. **`mayores(numeros, limite)`** → devuelve solo los números mayores que `limite` (usa `filter`).
5. **`longitudSegura(texto)`** → recibe un texto **opcional/nulable**; devuelve su longitud o `0` si es nulo.
6. **`Producto`** → modelo con `nombre` y `precio`. Implementa `precioConIva()` (21 %).
7. **`totalCarrito(productos)`** → suma del precio con IVA de todos los productos.
8. **`Semaforo`** → enum con tres colores; implementa `siguiente()` (verde → ámbar → rojo → verde).

## Pistas
<details>
<summary>Ver pistas</summary>

- Swift: `n % 2 == 0`, `numeros.reduce(0, +)`, `numeros.filter { $0 > limite }`, `texto?.count ?? 0`.
- Kotlin: `n % 2 == 0`, `numeros.sum()`, `numeros.filter { it > limite }`, `texto?.length ?: 0`.
- Para el enum usa `switch self` (Swift) / `when (this)` (Kotlin).
</details>

## Retos extra
- Añade una función `fizzBuzz(n)` que imprima del 1 al `n` con las reglas de FizzBuzz.
- Añade a `Producto` una propiedad `categoria` usando un `enum` y agrupa el carrito por categoría (`Dictionary(grouping:by:)` / `groupBy`).
- Ordena el carrito por precio de mayor a menor.
