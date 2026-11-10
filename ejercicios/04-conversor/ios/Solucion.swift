import SwiftUI

func celsiusAFahrenheit(_ c: Double) -> Double {
    c * 9 / 5 + 32
}

func fahrenheitACelsius(_ f: Double) -> Double {
    (f - 32) * 5 / 9
}

enum Modo: String, CaseIterable {
    case cAF = "°C → °F"
    case fAC = "°F → °C"
}

struct ConversorView: View {
    @State private var texto = ""
    @State private var modo = Modo.cAF

    // Propiedad calculada: se reevalúa cada vez que cambia `texto` o `modo`
    private var resultado: String? {
        guard let valor = Double(texto.replacingOccurrences(of: ",", with: ".")) else {
            return nil
        }
        switch modo {
        case .cAF: return String(format: "%.1f °F", celsiusAFahrenheit(valor))
        case .fAC: return String(format: "%.1f °C", fahrenheitACelsius(valor))
        }
    }

    var body: some View {
        Form {
            Section("Temperatura") {
                TextField("Ej: 25", text: $texto)
                    .keyboardType(.decimalPad)

                Picker("Modo", selection: $modo) {
                    ForEach(Modo.allCases, id: \.self) { modo in
                        Text(modo.rawValue)
                    }
                }
                .pickerStyle(.segmented)
            }

            Section("Resultado") {
                if let resultado {
                    Text(resultado)
                        .font(.title)
                } else {
                    Text("Introduce un número válido")
                        .foregroundStyle(.red)
                }
            }
        }
        .navigationTitle("Conversor")
    }
}

#Preview {
    NavigationStack {
        ConversorView()
    }
}
