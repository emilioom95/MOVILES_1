fun main() {
    // Solicitar al usuario los datos de dirección
    print("Ingrese el nombre de la calle: ")
    val calle = readLine()

    print("Ingrese la ciudad: ")
    val ciudad = readLine()

    print("Ingrese el estado o provincia: ")
    val estado = readLine()

    print("Ingrese el país: ")
    val pais = readLine()

    print("Ingrese el código postal: ")
    val codigoPostal = readLine()

    // Concatenar la dirección
    val direccion = "$calle, $ciudad, $estado, $pais, $codigoPostal"

    // Imprimir la dirección en consola
    println("La dirección del usuario es: $direccion")
}

