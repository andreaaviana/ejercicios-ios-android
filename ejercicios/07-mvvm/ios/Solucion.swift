import SwiftUI
import Observation

@Observable
final class JuegoViewModel {
    private var numeroSecreto: Int
    private let generarNumero: () -> Int

    private(set) var intentos = 0
    private(set) var mensaje = "Adivina un número del 1 al 100"
    private(set) var haGanado = false

    /// `generarNumero` se puede sustituir en los tests para que el número no sea aleatorio.
    init(generarNumero: @escaping () -> Int = { Int.random(in: 1...100) }) {
        self.generarNumero = generarNumero
        self.numeroSecreto = generarNumero()
    }

    convenience init(numeroSecreto: Int) {
        self.init(generarNumero: { numeroSecreto })
    }

    func intentar(_ texto: String) {
        guard !haGanado else { return }
        guard let numero = Int(texto.trimmingCharacters(in: .whitespaces)), (1...100).contains(numero) else {
            mensaje = "⚠️ Escribe un número entre 1 y 100"
            return
        }

        intentos += 1

        if numero < numeroSecreto {
            mensaje = "📈 Más alto"
        } else if numero > numeroSecreto {
            mensaje = "📉 Más bajo"
        } else {
            mensaje = "🎉 ¡Correcto! en \(intentos) intentos"
            haGanado = true
        }
    }

    func reiniciar() {
        numeroSecreto = generarNumero()
        intentos = 0
        mensaje = "Adivina un número del 1 al 100"
        haGanado = false
    }
}

struct JuegoView: View {
    @State private var vm = JuegoViewModel()
    @State private var texto = ""

    var body: some View {
        VStack(spacing: 20) {
            Text("Adivina el número")
                .font(.largeTitle).bold()

            Text(vm.mensaje)
                .font(.title2)

            Text("Intentos: \(vm.intentos)")
                .foregroundStyle(.secondary)

            if vm.haGanado {
                Button("Jugar otra vez") {
                    vm.reiniciar()
                    texto = ""
                }
                .buttonStyle(.borderedProminent)
            } else {
                HStack {
                    TextField("1 – 100", text: $texto)
                        .keyboardType(.numberPad)
                        .textFieldStyle(.roundedBorder)
                    Button("Probar") {
                        vm.intentar(texto)
                        texto = ""
                    }
                    .buttonStyle(.borderedProminent)
                }
            }
        }
        .padding()
    }
}

#Preview {
    JuegoView()
}

// Tests (en el target de tests de Xcode, con Swift Testing):
//
// import Testing
// @testable import TuApp
//
// struct JuegoViewModelTests {
//     @Test func numeroBajoPideMasAlto() {
//         let vm = JuegoViewModel(numeroSecreto: 50)
//         vm.intentar("10")
//         #expect(vm.mensaje.contains("alto"))
//         #expect(vm.intentos == 1)
//     }
//
//     @Test func numeroAltoPideMasBajo() {
//         let vm = JuegoViewModel(numeroSecreto: 50)
//         vm.intentar("90")
//         #expect(vm.mensaje.contains("bajo"))
//     }
//
//     @Test func acertarGana() {
//         let vm = JuegoViewModel(numeroSecreto: 50)
//         vm.intentar("10")
//         vm.intentar("50")
//         #expect(vm.haGanado)
//         #expect(vm.intentos == 2)
//     }
//
//     @Test func textoInvalidoNoCuentaComoIntento() {
//         let vm = JuegoViewModel(numeroSecreto: 50)
//         vm.intentar("hola")
//         #expect(vm.intentos == 0)
//     }
// }
