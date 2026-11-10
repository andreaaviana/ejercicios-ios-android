import SwiftUI

// MARK: - Modelo

struct RespuestaPersonajes: Decodable {
    let results: [Personaje]
}

struct Personaje: Decodable, Identifiable {
    let id: Int
    let name: String
    let status: String
    let species: String
    let image: URL
}

// MARK: - Servicio

struct PersonajesService {
    private let url = URL(string: "https://rickandmortyapi.com/api/character")!

    func obtenerPersonajes() async throws -> [Personaje] {
        let (data, respuesta) = try await URLSession.shared.data(from: url)
        guard let http = respuesta as? HTTPURLResponse, http.statusCode == 200 else {
            throw URLError(.badServerResponse)
        }
        return try JSONDecoder().decode(RespuestaPersonajes.self, from: data).results
    }
}

// MARK: - ViewModel

enum EstadoPantalla {
    case cargando
    case error(String)
    case exito([Personaje])
}

@MainActor
@Observable
final class PersonajesViewModel {
    private(set) var estado = EstadoPantalla.cargando
    private let service = PersonajesService()

    func cargar() async {
        estado = .cargando
        do {
            let personajes = try await service.obtenerPersonajes()
            estado = .exito(personajes)
        } catch {
            estado = .error("No se pudieron cargar los personajes.\n\(error.localizedDescription)")
        }
    }
}

// MARK: - Vista

struct PersonajesView: View {
    @State private var vm = PersonajesViewModel()

    var body: some View {
        NavigationStack {
            Group {
                switch vm.estado {
                case .cargando:
                    ProgressView("Cargando…")

                case .error(let mensaje):
                    ContentUnavailableView {
                        Label("Error", systemImage: "wifi.exclamationmark")
                    } description: {
                        Text(mensaje)
                    } actions: {
                        Button("Reintentar") {
                            Task { await vm.cargar() }
                        }
                        .buttonStyle(.borderedProminent)
                    }

                case .exito(let personajes):
                    List(personajes) { personaje in
                        FilaPersonaje(personaje: personaje)
                    }
                    .refreshable { await vm.cargar() }
                }
            }
            .navigationTitle("Personajes")
            .task { await vm.cargar() }
        }
    }
}

struct FilaPersonaje: View {
    let personaje: Personaje

    private var colorEstado: Color {
        switch personaje.status {
        case "Alive": .green
        case "Dead": .red
        default: .gray
        }
    }

    var body: some View {
        HStack(spacing: 12) {
            AsyncImage(url: personaje.image) { imagen in
                imagen.resizable().scaledToFill()
            } placeholder: {
                Color.gray.opacity(0.2)
            }
            .frame(width: 60, height: 60)
            .clipShape(RoundedRectangle(cornerRadius: 8))

            VStack(alignment: .leading, spacing: 4) {
                Text(personaje.name).font(.headline)
                HStack(spacing: 6) {
                    Circle().fill(colorEstado).frame(width: 8, height: 8)
                    Text("\(personaje.species) · \(personaje.status)")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
            }
        }
    }
}

#Preview {
    PersonajesView()
}
