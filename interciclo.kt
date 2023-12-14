class Libro(val titulo: String, val autor: String, val anioPublicacion: Int, var disponible: Boolean = true) {
    override fun toString(): String {
        val estado = if (disponible) "disponible" else "no disponible"
        return "Libro: $titulo - Autor: $autor - Año de publicación: $anioPublicacion - Estado: $estado"
    }
}

class Usuario(val nombre: String, val edad: Int) {
    val librosPrestados = mutableListOf<Libro>()

    fun tomarPrestado(libro: Libro) {
        if (libro.disponible) {
            librosPrestados.add(libro)
            libro.disponible = false
            println("$nombre ha tomado prestado el libro ${libro.titulo}")
        } else {
            println("Lo siento, el libro ${libro.titulo} no está disponible en este momento.")
        }
    }

    override fun toString(): String {
        return "Usuario: $nombre - Edad: $edad - Libros prestados: ${librosPrestados.size}"
    }
}

class Biblioteca {
    val librosDisponibles = mutableListOf<Libro>()
    val usuariosRegistrados = mutableListOf<Usuario>()

    fun registrarUsuario(usuario: Usuario) {
        usuariosRegistrados.add(usuario)
        println("Usuario registrado: ${usuario.nombre}")
    }

    fun agregarLibro(libro: Libro) {
        librosDisponibles.add(libro)
        println("Libro agregado al inventario: ${libro.titulo}")
    }

    fun prestarLibro(usuario: Usuario, libro: Libro) {
        if (librosDisponibles.contains(libro) && libro.disponible) {
            usuario.tomarPrestado(libro)
            println("Libro prestado a ${usuario.nombre}: ${libro.titulo}")
        } else {
            println("No se puede prestar el libro ${libro.titulo} a ${usuario.nombre}.")
        }
    }

    fun mostrarLibrosDisponibles() {
        println("Libros disponibles en la biblioteca:")
        librosDisponibles.forEach { println(it) }
    }

    fun mostrarUsuariosRegistrados() {
        println("Usuarios registrados en la biblioteca:")
        usuariosRegistrados.forEach { println(it) }
    }
}

fun main() {
    // Crear la biblioteca
    val biblioteca = Biblioteca()

    // Registrar usuarios
    val usuario1 = Usuario("Emilio", 28)
    val usuario2 = Usuario("Jose", 29)
    val usuario3 = Usuario("Andrey", 21)
    biblioteca.registrarUsuario(usuario1)
    biblioteca.registrarUsuario(usuario2)
    biblioteca.registrarUsuario(usuario3)

    // Agregar libros al inventario
    val libro1 = Libro("Harry Potter y el prisionero de Azkban", "J.K. Rowling", 2004)
    val libro2 = Libro("The Great Gatsby", "F. Scott Fitzgerald", 1925)
    val libro3 = Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605)
    val libro4 = Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1954)
    val libro5 = Libro("Orgullo y prejuicio", "Jane Austen", 1813)
    val libro6 = Libro("El código Da Vinci", "Dan Brown", 2003)

    biblioteca.agregarLibro(libro1)
    biblioteca.agregarLibro(libro2)
    biblioteca.agregarLibro(libro3)
    biblioteca.agregarLibro(libro4)
    biblioteca.agregarLibro(libro5)
    biblioteca.agregarLibro(libro6)

    // Prestar libros a diferentes usuarios
    biblioteca.prestarLibro(usuario1, libro1)
    biblioteca.prestarLibro(usuario1, libro2)
    biblioteca.prestarLibro(usuario2, libro3)
    biblioteca.prestarLibro(usuario3, libro6)

    // Mostrar información de libros y usuarios
    println("Libros disponibles:")
    biblioteca.mostrarLibrosDisponibles()

    println("\nUsuarios registrados:")
    biblioteca.mostrarUsuariosRegistrados()
}


