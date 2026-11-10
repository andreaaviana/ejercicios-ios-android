import SwiftUI

struct ContadorView: View {
    // TODO 1: declara aquí el estado del contador

    var body: some View {
        VStack(spacing: 24) {
            Text("Contador")
                .font(.title)

            // TODO 2: muestra el número en grande
            // TODO 5: rojo si es mayor que 10

            HStack {
                // TODO 3: botones −, Reset y +
                // TODO 4: desactiva − cuando el contador sea 0
            }
        }
    }
}

#Preview {
    ContadorView()
}
