fun main() {
    // Declarar los arreglos de alumnos y calificaciones
    val alumnos = arrayOf("José", "Alberto", "Laura", "Noel", "Erika", "Daniel")
    val calificaciones = arrayOf(31.0, 94.0, 98.5, 75.0, 46.5, 75.0)

    // Imprimir la lista de alumnos y calificaciones
    println("Lista de Alumnos\tCalificaciones")
    for (i in alumnos.indices) {
        println("${alumnos[i]}\t\t\t${calificaciones[i]}")
    }

    // Calcular e imprimir el promedio, calificación más alta, calificación más baja y alumnos reprobados
    val promedio = calcularPromedio(calificaciones)
    val calificacionMaxima = calcularCalificacionMaxima(calificaciones)
    val calificacionMinima = calcularCalificacionMinima(calificaciones)
    val alumnosReprobados = obtenerAlumnosReprobados(alumnos, calificaciones)

    println("\nPromedio de calificaciones: ${"%.2f".format(promedio)}")
    println("Calificación más alta: $calificacionMaxima")
    println("Calificación más baja: $calificacionMinima")
    println("Alumnos reprobados:")
    for ((alumno, calificacion) in alumnosReprobados) {
        println("$alumno\t\t\t$calificacion")
    }
}

fun calcularPromedio(calificaciones: Array<Double>): Double {
    var suma = 0.0
    for (calificacion in calificaciones) {
        suma += calificacion
    }
    return suma / calificaciones.size
}

fun calcularCalificacionMaxima(calificaciones: Array<Double>): Double {
    return calificaciones.maxOrNull() ?: 0.0
}

fun calcularCalificacionMinima(calificaciones: Array<Double>): Double {
    return calificaciones.minOrNull() ?: 0.0
}

fun obtenerAlumnosReprobados(alumnos: Array<String>, calificaciones: Array<Double>): List<Pair<String, Double>> {
    val alumnosReprobados = mutableListOf<Pair<String, Double>>()
    for (i in calificaciones.indices) {
        if (calificaciones[i] < 60.0) {
            alumnosReprobados.add(alumnos[i] to calificaciones[i])
        }
    }
    return alumnosReprobados
}
