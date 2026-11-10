import SwiftUI

// TODO 1: modelo Articulo (Identifiable) con id, nombre y comprado
struct Articulo {
}

struct ListaCompraView: View {
    // TODO 2: estado con la lista de artículos
    @State private var nuevoNombre = ""

    var body: some View {
        VStack {
            HStack {
                TextField("Escribe un producto...", text: $nuevoNombre)
                    .textFieldStyle(.roundedBorder)
                Button("Añadir") {
                    // TODO 3: añadir (sin vacíos) y limpiar el campo
                }
            }
            .padding()

            // TODO 4: List con los artículos (tocar = alternar comprado, tachado si comprado)
            // TODO 5: .onDelete para borrar
            // TODO 7: mensaje si la lista está vacía

            // TODO 6: "N pendientes"
        }
        .navigationTitle("Lista de la compra")
    }
}

#Preview {
    NavigationStack {
        ListaCompraView()
    }
}
