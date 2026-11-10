import SwiftUI
import Observation

@Observable
final class JuegoViewModel {
    // TODO 1: estado
    // private var numeroSecreto: Int
    // private(set) var intentos = 0
    // private(set) var mensaje = "Adivina un número del 1 al 100"
    // private(set) var haGanado = false

    // TODO 4: permite inyectar el número secreto
    init() {
    }

    func intentar(_ texto: String) {
        // TODO 1: valida el texto, cuenta el intento y actualiza el mensaje
    }

    func reiniciar() {
        // TODO 1
    }
}

struct JuegoView: View {
    @State private var vm = JuegoViewModel()
    @State private var texto = ""

    var body: some View {
        VStack(spacing: 20) {
            // TODO 2: campo, botón Probar, mensaje, intentos y botón Jugar otra vez
            // TODO 3: ¡nada de lógica aquí! solo llamar a vm.intentar(...) y vm.reiniciar()
            Text("Empieza aquí")
        }
        .padding()
    }
}

#Preview {
    JuegoView()
}

// TODO 5: tests. En Xcode: File → New → Target → Unit Testing Bundle.
// Ejemplo con Swift Testing:
//
// import Testing
// @testable import TuApp
//
// @Test func numeroMasAlto() {
//     let vm = JuegoViewModel(numeroSecreto: 50)
//     vm.intentar("10")
//     #expect(vm.mensaje.contains("alto"))
//     #expect(vm.intentos == 1)
// }
