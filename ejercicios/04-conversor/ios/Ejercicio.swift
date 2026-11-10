import SwiftUI

// TODO 5: implementa la lógica fuera de la vista
func celsiusAFahrenheit(_ c: Double) -> Double {
    0
}

func fahrenheitACelsius(_ f: Double) -> Double {
    0
}

enum Modo: String, CaseIterable {
    case cAF = "°C → °F"
    case fAC = "°F → °C"
}

struct ConversorView: View {
    @State private var texto = ""
    @State private var modo = Modo.cAF

    var body: some View {
        Form {
            // TODO 1: TextField con teclado numérico
            // TODO 2: Picker segmentado con los casos de Modo
            // TODO 3 y 4: resultado con un decimal, o mensaje de error en rojo
            Text("Empieza aquí")
        }
        .navigationTitle("Conversor")
    }
}

#Preview {
    NavigationStack {
        ConversorView()
    }
}
