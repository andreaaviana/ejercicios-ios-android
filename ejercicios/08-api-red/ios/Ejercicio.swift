import SwiftUI

// MARK: - Modelo

// TODO 1: modelos Decodable que encajen con el JSON
struct RespuestaPersonajes: Decodable {
    // let results: [Personaje]
}

struct Personaje: Decodable, Identifiable {
    let id: Int
    // TODO: name, status, species, image
}

// MARK: - Servicio

struct PersonajesService {
    func obtenerPersonajes() async throws -> [Personaje] {
        // TODO 2: URLSession.shared.data(from:) + JSONDecoder
        []
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
        // TODO 3: pon estado = .cargando, llama al servicio y pasa a .exito o .error
    }
}

// MARK: - Vista

struct PersonajesView: View {
    @State private var vm = PersonajesViewModel()

    var body: some View {
        NavigationStack {
            Group {
                // TODO 5: switch vm.estado { case .cargando: ProgressView() ... }
                Text("Empieza aquí")
            }
            .navigationTitle("Personajes")
            // TODO 4: .task { await vm.cargar() }
        }
    }
}

struct FilaPersonaje: View {
    let personaje: Personaje

    var body: some View {
        // TODO 6: AsyncImage + nombre + especie + círculo de color según status
        Text("\(personaje.id)")
    }
}

#Preview {
    PersonajesView()
}
