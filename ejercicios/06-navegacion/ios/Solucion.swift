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
        NavigationStack {
            List(paisesEjemplo) { pais in
                NavigationLink(value: pais) {
                    HStack {
                        Text(pais.bandera).font(.largeTitle)
                        Text(pais.nombre)
                    }
                }
            }
            .navigationTitle("Países")
            .navigationDestination(for: Pais.self) { pais in
                DetallePaisView(pais: pais)
            }
        }
    }
}

struct DetallePaisView: View {
    let pais: Pais

    var body: some View {
        VStack(spacing: 16) {
            Text(pais.bandera)
                .font(.system(size: 120))
            Text(pais.nombre)
                .font(.largeTitle).bold()

            Grid(alignment: .leading, verticalSpacing: 8) {
                GridRow {
                    Text("Capital").foregroundStyle(.secondary)
                    Text(pais.capital)
                }
                GridRow {
                    Text("Población").foregroundStyle(.secondary)
                    Text("\(pais.poblacionMillones, specifier: "%.1f") millones")
                }
            }
            Spacer()
        }
        .padding()
        .navigationTitle(pais.nombre)
        .navigationBarTitleDisplayMode(.inline)
    }
}

#Preview {
    ListaPaisesView()
}
