import kotlin.math.roundToInt

fun main() {
    // Solicitar al vendedor los kilómetros del trayecto
    print("Ingrese los kilómetros del trayecto: ")
    val kilometros = readLine()?.toDoubleOrNull() ?: 0.0

    // Solicitar al vendedor si se debe aplicar el descuento a personas de la tercera edad
    print("¿Aplica el descuento a personas de la tercera edad? (si/1 para aplicar descuento, cualquier otro valor para no aplicar descuento): ")
    val aplicaDescuento = readLine()?.toLowerCase() == "si" || readLine() == "1"

    // Calcular el total a cobrar con o sin descuento
    val total = if (aplicaDescuento) {
        calcularTotalConDescuento(kilometros)
    } else {
        calcularTotalSinDescuento(kilometros)
    }

    // Imprimir el total redondeado en números enteros
    println("Total a cobrar: \$${total.roundToInt()}")
}

fun calcularTotalConDescuento(kilometros: Double): Double {
    val tarifaPorKilometro = 2.25
    val descuentoPorcentaje = 0.45

    val totalSinDescuento = kilometros * tarifaPorKilometro
    val descuento = totalSinDescuento * descuentoPorcentaje

    return totalSinDescuento - descuento
}

fun calcularTotalSinDescuento(kilometros: Double): Double {
    val tarifaPorKilometro = 2.25
    return kilometros * tarifaPorKilometro
}
