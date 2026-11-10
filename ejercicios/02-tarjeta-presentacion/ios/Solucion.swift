import SwiftUI

struct TarjetaView: View {
    var body: some View {
        VStack(spacing: 8) {
            Image(systemName: "person.circle.fill")
                .resizable()
                .frame(width: 120, height: 120)
                .foregroundStyle(.white)

            Text("Ana García")
                .font(.largeTitle)
                .bold()
                .foregroundStyle(.white)

            Text("Desarrolladora móvil")
                .font(.title3)
                .foregroundStyle(.white.opacity(0.8))

            VStack(alignment: .leading, spacing: 12) {
                FilaContacto(icono: "envelope.fill", texto: "ana@ejemplo.com")
                FilaContacto(icono: "phone.fill", texto: "+34 600 000 000")
                FilaContacto(icono: "globe", texto: "github.com/ana")
            }
            .padding()
            .background(.white.opacity(0.15), in: RoundedRectangle(cornerRadius: 12))
            .padding(.top, 32)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.indigo.gradient)
    }
}

struct FilaContacto: View {
    let icono: String
    let texto: String

    var body: some View {
        HStack(spacing: 12) {
            Image(systemName: icono)
                .frame(width: 24)
            Text(texto)
        }
        .foregroundStyle(.white)
    }
}

#Preview {
    TarjetaView()
}
