# 03 · Contador

## Objetivo
Entender el concepto más importante de SwiftUI y Compose: **el estado**. Cuando un valor de estado cambia, la interfaz se vuelve a dibujar sola.

## Resultado esperado

```
        Contador

          7

   [ − ]  [ Reset ]  [ + ]
```

## Pasos
1. Guarda el número en una variable de estado.
   - iOS: `@State private var contador = 0`
   - Android: `var contador by remember { mutableIntStateOf(0) }`
2. Muestra el número en grande en el centro.
3. Tres botones: `−` resta 1, `+` suma 1, `Reset` lo vuelve a 0.
4. **Regla:** el contador no puede bajar de 0. Desactiva el botón `−` cuando esté en 0.
5. El número se pinta **rojo** si es mayor que 10.

## Prueba esto (y entiende por qué pasa)
- Quita `@State` (iOS) o `remember` (Android). ¿Qué ocurre? ¿Compila? ¿Funciona el botón?
- En Android, cambia `remember` por `rememberSaveable` y **gira el emulador**. Compara con `remember`. ¿Qué diferencia hay?

## Pistas
<details>
<summary>Ver pistas</summary>

- Desactivar un botón: `.disabled(contador == 0)` (iOS) / `Button(enabled = contador > 0, ...)` (Android).
- Color condicional: `.foregroundStyle(contador > 10 ? .red : .primary)` / `color = if (contador > 10) Color.Red else Color.Unspecified`.
- En Compose necesitas importar `androidx.compose.runtime.getValue` y `setValue` para usar `by`.
</details>

## Retos extra
- Añade un `Stepper` (iOS) o un `Slider` para elegir de cuánto en cuánto se suma (1, 5, 10).
- Anima el cambio de número (`.contentTransition(.numericText())` + `withAnimation` / `AnimatedContent`).
- Extrae los botones a un componente `BotonesContador` que reciba el valor y las acciones. (iOS: con `@Binding`; Android: pasando lambdas `onSumar`, `onRestar`…). Esto se llama **state hoisting** y lo usarás mucho.
