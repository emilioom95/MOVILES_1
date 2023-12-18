package DEBER2

import java.time.LocalDate

// Clase para representar un libro
data class Libro(
    val isbn: String,
    val titulo: String,
    val autor: String,
    var cantidadDisponible: Int,
    val fechaAdquisicion: LocalDate,
    val genero: String
)

// Clase para representar un usuario de la biblioteca
data class Usuario(
    val id: Int,
    val nombre: String,
    val numeroIdentificacion: String,
    val direccion: String,
    val librosPrestados: MutableList<Libro> = mutableListOf()
)

// Clase para representar una transacción
data class Transaccion(val tipo: TipoTransaccion, val usuario: Usuario, val libro: Libro, val fecha: LocalDate)

enum class TipoTransaccion {
    PRESTAMO,
    DEVOLUCION,
    ADICION_INVENTARIO
}

// Clase principal que gestiona la biblioteca
class Biblioteca {
    private val inventario = mutableListOf<Libro>()
    private val usuarios = mutableListOf<Usuario>()
    private val historialTransacciones = mutableListOf<Transaccion>()

    // Función para agregar un libro al inventario
    fun agregarLibro(libro: Libro) {
        inventario.add(libro)
        historialTransacciones.add(Transaccion(TipoTransaccion.ADICION_INVENTARIO,
            Usuario(0, "Sistema", "", ""), libro, LocalDate.now()))
    }

    // Función para registrar un nuevo usuario
    fun registrarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    // Función para prestar un libro a un usuario
    fun prestarLibro(usuario: Usuario, isbn: String) {
        val libro = inventario.find { it.isbn == isbn }

        if (libro != null && libro.cantidadDisponible > 0) {
            usuario.librosPrestados.add(libro)
            libro.cantidadDisponible--
            historialTransacciones.add(Transaccion(TipoTransaccion.PRESTAMO, usuario, libro, LocalDate.now()))
            println("Libro prestado a ${usuario.nombre}: ${libro.titulo}")
        } else {
            println("El libro no está disponible para préstamo.")
        }
    }

    // Función para recibir la devolución de un libro
    fun recibirDevolucion(usuario: Usuario, isbn: String) {
        val libro = usuario.librosPrestados.find { it.isbn == isbn }

        if (libro != null) {
            usuario.librosPrestados.remove(libro)
            libro.cantidadDisponible++
            historialTransacciones.add(Transaccion(TipoTransaccion.DEVOLUCION, usuario, libro, LocalDate.now()))
            println("Libro devuelto por ${usuario.nombre}: ${libro.titulo}")
        } else {
            println("El usuario no tiene este libro prestado.")
        }
    }

    // Función para buscar libros por título, autor o género
    fun buscarLibros(criterio: String) {
        val resultados = inventario.filter {
            it.titulo.contains(criterio, ignoreCase = true) ||
                    it.autor.contains(criterio, ignoreCase = true) ||
                    it.genero.contains(criterio, ignoreCase = true)
        }

        if (resultados.isNotEmpty()) {
            println("Resultados de la búsqueda:")
            resultados.forEach { println("${it.isbn}: ${it.titulo} - ${it.autor} - ${it.genero}") }
        } else {
            println("No se encontraron resultados para el criterio de búsqueda.")
        }
    }

    // Función para mostrar el historial de transacciones
    fun mostrarHistorialTransacciones() {
        println("Historial de transacciones:")
        historialTransacciones.forEach {
            println("${it.fecha} - ${it.tipo} - Usuario: ${it.usuario.nombre}, Libro: ${it.libro.titulo}")
        }
    }

    // Función para mostrar información sobre los libros disponibles en el inventario
    fun mostrarInventario() {
        println("Inventario:")
        inventario.forEach { println("${it.isbn}: ${it.titulo} - Disponibles: ${it.cantidadDisponible}") }
    }

    // Función para mostrar información sobre los libros prestados por un usuario
    fun mostrarLibrosPrestados(usuario: Usuario) {
        println("Libros prestados a ${usuario.nombre}:")
        usuario.librosPrestados.forEach { println("${it.isbn}: ${it.titulo}") }
    }
}

fun main() {
    val biblioteca = Biblioteca()

    val libro1 = Libro("ISBN123", "El Señor de los Anillos", "J.R.R. Tolkien", 5, LocalDate.now(), "Fantasía")
    val libro2 = Libro("ISBN456", "Cien años de soledad", "Gabriel García Márquez", 3, LocalDate.now(), "Realismo mágico")

    biblioteca.agregarLibro(libro1)
    biblioteca.agregarLibro(libro2)

    val usuario1 = Usuario(1, "Juan", "ID123", "Calle 123, Ciudad")
    val usuario2 = Usuario(2, "Maria", "ID456", "Avenida 456, Pueblo")

    biblioteca.registrarUsuario(usuario1)
    biblioteca.registrarUsuario(usuario2)

    biblioteca.prestarLibro(usuario1, "ISBN123")
    biblioteca.prestarLibro(usuario2, "ISBN456")

    biblioteca.mostrarInventario()
    biblioteca.mostrarLibrosPrestados(usuario1)

    biblioteca.recibirDevolucion(usuario1, "ISBN123")

    biblioteca.mostrarInventario()
    biblioteca.mostrarLibrosPrestados(usuario1)

    biblioteca.buscarLibros("Tolkien")
    biblioteca.mostrarHistorialTransacciones()
}
