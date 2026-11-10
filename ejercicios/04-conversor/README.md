# 04 · Conversor de temperatura

## Objetivo
Leer lo que escribe el usuario con un **campo de texto**, convertirlo a número de forma segura y mostrar un resultado calculado.

## Resultado esperado

```
  Conversor de temperatura

  [ 25                    ]   ← el usuario escribe
  ( °C → °F )  ( °F → °C )    ← selector

  Resultado: 77.0 °F
```

## Pasos
1. Un campo de texto donde el usuario escribe la temperatura. Usa el **teclado numérico**.
2. Un selector con dos opciones: `°C → °F` y `°F → °C`.
3. Muestra el resultado con **un decimal**. Se recalcula automáticamente al escribir o cambiar el selector (no hace falta botón).
4. Si el texto no es un número válido (vacío, "abc"…), muestra `Introduce un número válido` en rojo.
5. Pon la lógica de conversión en **funciones aparte**, fuera de la vista: `celsiusAFahrenheit(_:)` y `fahrenheitACelsius(_:)`.

Fórmulas: `F = C × 9/5 + 32` · `C = (F − 32) × 5/9`

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS:
  - `TextField("Temperatura", text: $texto).keyboardType(.decimalPad)`
  - `Double(texto)` devuelve un `Double?` (nil si no es número).
  - Selector: `Picker("Modo", selection: $modo) { ... }.pickerStyle(.segmented)` con un `enum Modo: CaseIterable`.
  - Formato: `String(format: "%.1f", valor)` o `valor.formatted(.number.precision(.fractionLength(1)))`.
  - Ojo: con teclado en español el separador decimal es la coma. Prueba `Double(texto.replacingOccurrences(of: ",", with: "."))`.
- Android:
  - `OutlinedTextField(value = texto, onValueChange = { texto = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))`
  - `texto.toDoubleOrNull()` devuelve `Double?`.
  - Selector: dos `FilterChip` o `SingleChoiceSegmentedButtonRow`.
  - Formato: `"%.1f".format(valor)`.
</details>

## Retos extra
- Añade Kelvin como tercera unidad (elige unidad de origen y de destino).
- Escribe **tests unitarios** para las funciones de conversión (iOS: target de tests con `XCTest` o `Swift Testing`; Android: `src/test` con JUnit). Ese es el motivo de sacarlas de la vista.
- Muestra un emoji según la temperatura en °C: 🥶 < 10, 😊 10–25, 🥵 > 25.
