// 01 · Fundamentos de Swift
// Ejecuta con: swift Ejercicio.swift

// 1. Devuelve "Hola, <nombre>!"
func saludar(_ nombre: String) -> String {
    // TODO
    return ""
}

// 2. true si n es par
func esPar(_ n: Int) -> Bool {
    // TODO
    return false
}

// 3. Suma todos los números
func sumaLista(_ numeros: [Int]) -> Int {
    // TODO
    return 0
}

// 4. Solo los números mayores que `limite`
func mayores(_ numeros: [Int], que limite: Int) -> [Int] {
    // TODO
    return []
}

// 5. Longitud del texto, o 0 si es nil
func longitudSegura(_ texto: String?) -> Int {
    // TODO
    return -1
}

// 6. Modelo de producto
struct Producto {
    let nombre: String
    let precio: Double

    func precioConIva() -> Double {
        // TODO: añade un 21 %
        return 0
    }
}

// 7. Suma de los precios con IVA
func totalCarrito(_ productos: [Producto]) -> Double {
    // TODO
    return 0
}

// 8. Semáforo: verde -> ambar -> rojo -> verde
enum Semaforo {
    case verde, ambar, rojo

    func siguiente() -> Semaforo {
        // TODO
        return self
    }
}

// ---------------------------------------------------------------
// Comprobaciones: no hace falta tocar nada a partir de aquí
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
