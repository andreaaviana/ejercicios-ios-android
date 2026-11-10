import SwiftUI
import SwiftData

// Recuerda añadir .modelContainer(for: Registro.self) en tu archivo App (ver README)

@Model
final class Registro {
    var fecha: Date
    var emoji: String
    var nota: String

    init(fecha: Date = .now, emoji: String, nota: String = "") {
        self.fecha = fecha
        self.emoji = emoji
        self.nota = nota
    }
}

struct DiarioView: View {
    @AppStorage("nombre") private var nombre = ""
    @Environment(\.modelContext) private var context
    @Query(sort: \Registro.fecha, order: .reverse) private var registros: [Registro]

    @State private var emojiSeleccionado = "🙂"
    @State private var nota = ""
    @State private var nombreTemporal = ""

    private let emojis = ["😄", "🙂", "😐", "🙁", "😢"]

    var body: some View {
        NavigationStack {
            Form {
                if nombre.isEmpty {
                    Section("¿Cómo te llamas?") {
                        TextField("Tu nombre", text: $nombreTemporal)
                        Button("Guardar nombre") { nombre = nombreTemporal }
                            .disabled(nombreTemporal.isEmpty)
                    }
                } else {
                    Text("Hola, \(nombre) 👋")
                        .font(.title2)
                }

                Section("¿Cómo te sientes hoy?") {
                    HStack {
                        ForEach(emojis, id: \.self) { emoji in
                            Text(emoji)
                                .font(.largeTitle)
                                .opacity(emoji == emojiSeleccionado ? 1 : 0.3)
                                .onTapGesture { emojiSeleccionado = emoji }
                                .frame(maxWidth: .infinity)
                        }
                    }
                    TextField("Nota opcional...", text: $nota)
                    Button("Guardar", action: guardar)
                }

                Section("Historial") {
                    if registros.isEmpty {
                        Text("Aún no hay registros").foregroundStyle(.secondary)
                    }
                    ForEach(registros) { registro in
                        HStack {
                            Text(registro.emoji).font(.title)
                            VStack(alignment: .leading) {
                                Text(registro.fecha, format: .dateTime.day().month().hour().minute())
                                    .font(.subheadline)
                                if !registro.nota.isEmpty {
                                    Text(registro.nota).foregroundStyle(.secondary)
                                }
                            }
                        }
                    }
                    .onDelete { indices in
                        for i in indices { context.delete(registros[i]) }
                    }
                }
            }
            .navigationTitle("Diario")
            .toolbar {
                if !nombre.isEmpty {
                    Button("Cambiar nombre") { nombre = "" }
                }
            }
        }
    }

    private func guardar() {
        context.insert(Registro(emoji: emojiSeleccionado, nota: nota))
        nota = ""
    }
}

#Preview {
    DiarioView()
        .modelContainer(for: Registro.self, inMemory: true)
}
