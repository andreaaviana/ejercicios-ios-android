import SwiftUI

struct Pais: Identifiable, Hashable {
    let id: Int
    let nombre: String
    let bandera: String
    let capital: String
    let poblacionMillones: Double
}

let paisesEjemplo = [
    Pais(id: 1, nombre: "España", bandera: "🇪🇸", capital: "Madrid", poblacionMillones: 48.6),
    Pais(id: 2, nombre: "Francia", bandera: "🇫🇷", capital: "París", poblacionMillones: 68.4),
    Pais(id: 3, nombre: "Italia", bandera: "🇮🇹", capital: "Roma", poblacionMillones: 58.9),
    Pais(id: 4, nombre: "Portugal", bandera: "🇵🇹", capital: "Lisboa", poblacionMillones: 10.6),
    Pais(id: 5, nombre: "México", bandera: "🇲🇽", capital: "Ciudad de México", poblacionMillones: 130.0),
    Pais(id: 6, nombre: "Argentina", bandera: "🇦🇷", capital: "Buenos Aires", poblacionMillones: 46.0),
]

struct ListaPaisesView: View {
    var body: some View {
        // TODO 2 y 3: NavigationStack + List + NavigationLink(value:) + navigationDestination
        Text("Empieza aquí")
    }
}

struct DetallePaisView: View {
    let pais: Pais

    var body: some View {
        // TODO 4: muestra todos los datos del país
        // TODO 5: .navigationTitle(pais.nombre)
        Text(pais.nombre)
    }
}

#Preview {
    ListaPaisesView()
}
