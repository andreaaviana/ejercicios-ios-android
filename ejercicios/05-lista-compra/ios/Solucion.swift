import SwiftUI

struct Articulo: Identifiable {
    let id = UUID()
    var nombre: String
    var comprado = false
}

struct ListaCompraView: View {
    @State private var articulos: [Articulo] = [
        Articulo(nombre: "Leche"),
        Articulo(nombre: "Pan", comprado: true),
    ]
    @State private var nuevoNombre = ""

    private var pendientes: Int {
        articulos.filter { !$0.comprado }.count
    }

    var body: some View {
        VStack {
            HStack {
                TextField("Escribe un producto...", text: $nuevoNombre)
                    .textFieldStyle(.roundedBorder)
                    .onSubmit(anadir)
                Button("Añadir", action: anadir)
                    .disabled(nuevoNombre.trimmingCharacters(in: .whitespaces).isEmpty)
            }
            .padding()

            if articulos.isEmpty {
                ContentUnavailableView("Tu lista está vacía 🛒", systemImage: "cart")
            } else {
                List {
                    ForEach($articulos) { $articulo in
                        Button {
                            articulo.comprado.toggle()
                        } label: {
                            HStack {
                                Image(systemName: articulo.comprado ? "checkmark.square.fill" : "square")
                                Text(articulo.nombre)
                                    .strikethrough(articulo.comprado)
                                    .foregroundStyle(articulo.comprado ? .secondary : .primary)
                            }
                        }
                        .buttonStyle(.plain)
                    }
                    .onDelete { articulos.remove(atOffsets: $0) }
                }
            }

            Text("\(pendientes) pendientes")
                .foregroundStyle(.secondary)
                .padding(.bottom)
        }
        .navigationTitle("Lista de la compra")
    }

    private func anadir() {
        let nombre = nuevoNombre.trimmingCharacters(in: .whitespaces)
        guard !nombre.isEmpty else { return }
        articulos.append(Articulo(nombre: nombre))
        nuevoNombre = ""
    }
}

#Preview {
    NavigationStack {
        ListaCompraView()
    }
}
