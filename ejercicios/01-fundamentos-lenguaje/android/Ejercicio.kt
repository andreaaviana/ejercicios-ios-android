// 01 · Fundamentos de Kotlin
// Ejecuta en https://play.kotlinlang.org/ o con clic derecho → Run en Android Studio

import kotlin.math.abs

// 1. Devuelve "Hola, <nombre>!"
fun saludar(nombre: String): String {
    // TODO
    return ""
}

// 2. true si n es par
fun esPar(n: Int): Boolean {
    // TODO
    return false
}

// 3. Suma todos los números
fun sumaLista(numeros: List<Int>): Int {
    // TODO
    return 0
}

// 4. Solo los números mayores que `limite`
fun mayores(numeros: List<Int>, limite: Int): List<Int> {
    // TODO
    return emptyList()
}

// 5. Longitud del texto, o 0 si es null
fun longitudSegura(texto: String?): Int {
    // TODO
    return -1
}

// 6. Modelo de producto
data class Producto(val nombre: String, val precio: Double) {
    fun precioConIva(): Double {
        // TODO: añade un 21 %
        return 0.0
    }
}

// 7. Suma de los precios con IVA
fun totalCarrito(productos: List<Producto>): Double {
    // TODO
    return 0.0
}

// 8. Semáforo: VERDE -> AMBAR -> ROJO -> VERDE
enum class Semaforo {
    VERDE, AMBAR, ROJO;

    fun siguiente(): Semaforo {
        // TODO
        return this
    }
}

// ---------------------------------------------------------------
// Comprobaciones: no hace falta tocar nada a partir de aquí
// ---------------------------------------------------------------
fun comprobar(descripcion: String, ok: Boolean) {
    println(if (ok) "✅ $descripcion" else "❌ $descripcion")
}

fun main() {
    val carrito = listOf(Producto("Pan", 1.0), Producto("Leche", 2.0))

    comprobar("saludar", saludar("Ana") == "Hola, Ana!")
    comprobar("esPar", esPar(4) && !esPar(7))
    comprobar("sumaLista", sumaLista(listOf(1, 2, 3, 4)) == 10)
    comprobar("mayores", mayores(listOf(1, 5, 10, 20), 5) == listOf(10, 20))
    comprobar("longitudSegura", longitudSegura("hola") == 4 && longitudSegura(null) == 0)
    comprobar("precioConIva", abs(Producto("x", 100.0).precioConIva() - 121) < 0.001)
    comprobar("totalCarrito", abs(totalCarrito(carrito) - 3.63) < 0.001)
    comprobar("Semaforo", Semaforo.VERDE.siguiente() == Semaforo.AMBAR && Semaforo.ROJO.siguiente() == Semaforo.VERDE)
}
