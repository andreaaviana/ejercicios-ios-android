import SwiftUI
import SwiftData

// Recuerda añadir .modelContainer(for: Registro.self) en tu archivo App (ver README)

// TODO 4: modelo SwiftData
@Model
final class Registro {
    // var fecha: Date
    // var emoji: String
    // var nota: String

    init() {
    }
}

struct DiarioView: View {
    // TODO 1: @AppStorage("nombre") private var nombre = ""
    // TODO 5 y 6: @Environment(\.modelContext) y @Query ordenada por fecha descendente

    @State private var emojiSeleccionado = "🙂"
    @State private var nota = ""

    private let emojis = ["😄", "🙂", "😐", "🙁", "😢"]

    var body: some View {
        NavigationStack {
            Form {
                // TODO 2: saludo con el nombre, o un TextField para escribirlo si está vacío

                Section("¿Cómo te sientes hoy?") {
                    // TODO: fila de emojis seleccionables, campo de nota y botón Guardar (paso 5)
                }

                Section("Historial") {
                    // TODO 6 y 7: lista de registros con .onDelete
                }
            }
            .navigationTitle("Diario")
        }
    }
}

#Preview {
    DiarioView()
        .modelContainer(for: Registro.self, inMemory: true)
}
