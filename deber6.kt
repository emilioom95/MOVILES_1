fun main() {
    var suma = 0.0
    var cantidadNumeros = 0

    println("Ingrese números uno por uno. Ingrese 0 para finalizar.")

    // Leer números hasta que se ingrese un 0
    while (true) {
        print("Ingrese un número (o 0 para finalizar): ")
        val numero = readLine()?.toDoubleOrNull() ?: 0.0

        if (numero == 0.0) {
            break
        }

        suma += numero
        cantidadNumeros++
    }

    // Calcular el promedio
    val promedio = if (cantidadNumeros > 0) {
        suma / cantidadNumeros
    } else {
        0.0
    }

    // Imprimir la sumatoria y el promedio
    println("Sumatoria de los números: $suma")
    println("Promedio de los números: $promedio")
}


