import SwiftUI

struct ContadorView: View {
    @State private var contador = 0

    var body: some View {
        VStack(spacing: 24) {
            Text("Contador")
                .font(.title)

            Text("\(contador)")
                .font(.system(size: 80, weight: .bold))
                .foregroundStyle(contador > 10 ? .red : .primary)

            HStack(spacing: 16) {
                Button("−") { contador -= 1 }
                    .disabled(contador == 0)

                Button("Reset") { contador = 0 }

                Button("+") { contador += 1 }
            }
            .buttonStyle(.borderedProminent)
            .font(.title2)
        }
    }
}

#Preview {
    ContadorView()
}
