# 02 · Tarjeta de presentación

## Objetivo
Construir tu primera pantalla de verdad: una "tarjeta de visita" con tu foto (o un icono), nombre, profesión y datos de contacto.

Aprenderás a **componer layouts** (vertical, horizontal), usar **imágenes/iconos** y aplicar **estilos** (tamaño de letra, colores, márgenes, esquinas redondeadas).

## Resultado esperado

```
┌──────────────────────────────┐
│                              │
│            (  👤 )           │
│                              │
│          Ana García          │
│     Desarrolladora móvil     │
│                              │
│  ✉  ana@ejemplo.com          │
│  ☎  +34 600 000 000          │
│  🌐 github.com/ana           │
│                              │
└──────────────────────────────┘
```

## Pasos
1. Crea la vista/composable `TarjetaView` (iOS) / `TarjetaScreen` (Android).
2. Centra en vertical un icono circular grande, tu nombre (grande y en negrita) y tu profesión (más pequeña, en gris).
3. Debajo, una sección con 3 filas de contacto. Cada fila: icono + texto.
4. **Extrae la fila a un componente reutilizable** `FilaContacto(icono, texto)` en lugar de repetir código 3 veces.
5. Ponle un color de fondo a toda la pantalla.

## Pistas
<details>
<summary>Ver pistas</summary>

- iOS: `VStack(spacing:)`, `HStack`, `Image(systemName: "person.circle.fill")`, `.font(.largeTitle).bold()`, `.foregroundStyle(.secondary)`, `.frame(maxWidth: .infinity, maxHeight: .infinity)`, `.background(...)`.
  Para ver los iconos disponibles descarga la app gratuita **SF Symbols** de Apple.
- Android: `Column(horizontalAlignment = Alignment.CenterHorizontally)`, `Row(verticalAlignment = ...)`, `Icon(Icons.Default.Email, contentDescription = null)`, `fontSize = 32.sp`, `fontWeight = FontWeight.Bold`, `Modifier.fillMaxSize().background(...)`.
</details>

## Retos extra
- Usa una foto real: en iOS arrástrala a `Assets.xcassets`; en Android cópiala a `res/drawable`. Recórtala en círculo (`.clipShape(Circle())` / `Modifier.clip(CircleShape)`).
- Haz que la tarjeta se vea bien en **modo oscuro** (prueba a cambiarlo en el simulador/emulador).
- Añade una segunda `#Preview` / `@Preview` con otros datos.
