import kotlin.math.PI
fun main() {
    // Solicitar al usuario el radio del círculo en centímetros
    print("Ingrese el radio del círculo en centímetros: ")
    val radioCentimetros = readLine()?.toDoubleOrNull() ?: 0.0

    // Calcular el área y la circunferencia del círculo
    val areaCentimetros = calcularArea(radioCentimetros)
    val circunferenciaCentimetros = calcularCircunferencia(radioCentimetros)

    // Convertir los resultados a pulgadas
    val areaPulgadas = centimetrosAPulgadas(areaCentimetros)
    val circunferenciaPulgadas = centimetrosAPulgadas(circunferenciaCentimetros)

    // Imprimir los resultados en consola
    println("Área del círculo: ${"%.2f".format(areaCentimetros)} cm² (${"%.2f".format(areaPulgadas)} in²)")
    println("Circunferencia del círculo: ${"%.2f".format(circunferenciaCentimetros)} cm (${"%.2f".format(circunferenciaPulgadas)} in)")
}

fun calcularArea(radio: Double): Double {
    return PI * radio * radio
}

fun calcularCircunferencia(radio: Double): Double {
    return 2 * PI * radio
}

fun centimetrosAPulgadas(centimetros: Double): Double {
    // 1 cm = 0.393701 pulgadas
    return centimetros * 0.393701
}
