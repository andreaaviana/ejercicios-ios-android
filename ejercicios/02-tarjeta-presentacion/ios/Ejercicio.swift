import SwiftUI

struct TarjetaView: View {
    var body: some View {
        VStack {
            // TODO 1: icono grande (Image(systemName: "person.circle.fill"))
            // TODO 2: nombre en grande y negrita
            // TODO 3: profesión en gris
            // TODO 4: tres FilaContacto (email, teléfono, web)
            Text("Empieza aquí")
        }
        // TODO 5: que ocupe toda la pantalla y tenga color de fondo
    }
}

struct FilaContacto: View {
    let icono: String
    let texto: String

    var body: some View {
        // TODO: HStack con Image(systemName: icono) y Text(texto)
        Text(texto)
    }
}

#Preview {
    TarjetaView()
}
