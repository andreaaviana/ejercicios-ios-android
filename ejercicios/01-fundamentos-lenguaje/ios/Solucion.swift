// 01 · Fundamentos de Swift — SOLUCIÓN
// Ejecuta con: swift Solucion.swift

func saludar(_ nombre: String) -> String {
    "Hola, \(nombre)!"
}

func esPar(_ n: Int) -> Bool {
    n % 2 == 0
}

func sumaLista(_ numeros: [Int]) -> Int {
    numeros.reduce(0, +)
}

func mayores(_ numeros: [Int], que limite: Int) -> [Int] {
    numeros.filter { $0 > limite }
}

func longitudSegura(_ texto: String?) -> Int {
    texto?.count ?? 0
}

struct Producto {
    let nombre: String
    let precio: Double

    func precioConIva() -> Double {
        precio * 1.21
    }
}

func totalCarrito(_ productos: [Producto]) -> Double {
    productos.map { $0.precioConIva() }.reduce(0, +)
}

enum Semaforo {
    case verde, ambar, rojo

    func siguiente() -> Semaforo {
        switch self {
        case .verde: return .ambar
        case .ambar: return .rojo
        case .rojo: return .verde
        }
    }
}

// ---------------------------------------------------------------
func comprobar(_ descripcion: String, _ ok: Bool) {
    print(ok ? "✅ \(descripcion)" : "❌ \(descripcion)")
}

let carrito = [Producto(nombre: "Pan", precio: 1.0), Producto(nombre: "Leche", precio: 2.0)]

comprobar("saludar", saludar("Ana") == "Hola, Ana!")
comprobar("esPar", esPar(4) && !esPar(7))
comprobar("sumaLista", sumaLista([1, 2, 3, 4]) == 10)
comprobar("mayores", mayores([1, 5, 10, 20], que: 5) == [10, 20])
comprobar("longitudSegura", longitudSegura("hola") == 4 && longitudSegura(nil) == 0)
comprobar("precioConIva", abs(Producto(nombre: "x", precio: 100).precioConIva() - 121) < 0.001)
comprobar("totalCarrito", abs(totalCarrito(carrito) - 3.63) < 0.001)
comprobar("Semaforo", Semaforo.verde.siguiente() == .ambar && Semaforo.rojo.siguiente() == .verde)
