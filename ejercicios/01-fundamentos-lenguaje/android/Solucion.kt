// 01 · Fundamentos de Kotlin — SOLUCIÓN

import kotlin.math.abs

fun saludar(nombre: String): String = "Hola, $nombre!"

fun esPar(n: Int): Boolean = n % 2 == 0

fun sumaLista(numeros: List<Int>): Int = numeros.sum()

fun mayores(numeros: List<Int>, limite: Int): List<Int> = numeros.filter { it > limite }

fun longitudSegura(texto: String?): Int = texto?.length ?: 0

data class Producto(val nombre: String, val precio: Double) {
    fun precioConIva(): Double = precio * 1.21
}

fun totalCarrito(productos: List<Producto>): Double = productos.sumOf { it.precioConIva() }

enum class Semaforo {
    VERDE, AMBAR, ROJO;

    fun siguiente(): Semaforo = when (this) {
        VERDE -> AMBAR
        AMBAR -> ROJO
        ROJO -> VERDE
    }
}

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
