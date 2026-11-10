# 10 · Proyecto final: app de tareas

Ya sabes todo lo necesario para hacer una app completa. Aquí **no hay código de partida**: crea los proyectos desde cero en Xcode y Android Studio.

## La app: "Mis Tareas"

### Requisitos obligatorios
1. **Lista de tareas** con título, fecha límite opcional, prioridad (baja/media/alta) y estado (pendiente/hecha).
2. **Crear** tarea en una pantalla o *sheet* aparte, con validación (el título no puede estar vacío).
3. **Editar** una tarea al tocarla (navegación a detalle).
4. **Completar** con un toque en la casilla; las hechas se ven tachadas.
5. **Borrar** tareas.
6. **Filtro**: Todas / Pendientes / Hechas.
7. **Persistencia**: las tareas sobreviven al cerrar la app (SwiftData / Room).
8. **Arquitectura MVVM**: ninguna lógica de negocio dentro de las vistas.
9. **Estado vacío**: mensaje bonito cuando no hay tareas.
10. Al menos **5 tests unitarios** del ViewModel en cada plataforma.

### Requisitos opcionales (elige 2 o más)
- Ordenar por fecha límite o prioridad.
- Tareas vencidas en rojo.
- Buscador.
- Categorías con color.
- Notificación local el día de la fecha límite (`UserNotifications` / `NotificationManager` + `AlarmManager`/WorkManager).
- Widget en la pantalla de inicio (WidgetKit / Glance).
- Sincronizar con una API (por ejemplo <https://jsonplaceholder.typicode.com/todos>).
- Soporte de modo oscuro y textos accesibles (Dynamic Type / tamaño de fuente del sistema).
- Traducción a otro idioma (String Catalog / `strings.xml`).

## Estructura sugerida

```
iOS                                   Android
MisTareas/                            app/src/main/java/.../
├── MisTareasApp.swift                ├── MainActivity.kt
├── Modelo/                           ├── data/
│   └── Tarea.swift                   │   ├── Tarea.kt          (@Entity)
├── ViewModels/                       │   ├── TareaDao.kt
│   └── TareasViewModel.swift         │   └── TareasDatabase.kt
├── Vistas/                           ├── ui/
│   ├── ListaTareasView.swift         │   ├── TareasViewModel.kt
│   ├── FilaTareaView.swift           │   ├── ListaTareasScreen.kt
│   └── EditarTareaView.swift         │   └── EditarTareaScreen.kt
└── MisTareasTests/                   └── (src/test) TareasViewModelTest.kt
```

## Plan de trabajo recomendado
1. **Papel primero**: dibuja las pantallas a mano (o en Figma) antes de programar.
2. Modelo + ViewModel + tests (sin UI todavía).
3. Lista con datos falsos en memoria.
4. Crear / editar / borrar.
5. Persistencia.
6. Filtros y detalles visuales.
7. Opcionales.

Haz un **commit en git** al terminar cada paso. Es buena costumbre y te salvará más de una vez.

## Autoevaluación
Cuando termines, comprueba:

- [ ] ¿Funciona igual en iOS y en Android?
- [ ] ¿Gira la pantalla en Android sin perder lo que estabas escribiendo?
- [ ] ¿Qué pasa si escribes un título de 500 caracteres?
- [ ] ¿Las vistas solo pintan y llaman al ViewModel?
- [ ] ¿Los tests pasan? (⌘U en Xcode / `./gradlew test` en Android)
- [ ] ¿Podrías explicarle a otra persona qué hace cada archivo?

¡Enhorabuena! 🎉 Si has llegado aquí, ya tienes una app real en cada plataforma para tu portfolio.
