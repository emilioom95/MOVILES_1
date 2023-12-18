import java.util.*

// Clase para representar un User
data class User(val nombre: String, val numeroIdentificacion: String, val contrasena: String)

// Clase para representar una cuenta bancaria
data class CuentaBancaria(val numeroCuenta: String, val titular: User, var saldo: Double = 0.0) {
    fun consultarSaldo() {
        println("Saldo de la cuenta $numeroCuenta para ${titular.nombre}: $$saldo")
    }

    fun realizarDeposito(monto: Double) {
        saldo += monto
        println("Depósito exitoso. Nuevo saldo: $$saldo")
    }

    fun realizarRetiro(monto: Double) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto
            println("Retiro exitoso. Nuevo saldo: $$saldo")
        } else {
            println("Error: Fondos insuficientes.")
        }
    }
}

// Clase para representar el sistema de cajero automático
class CajeroAutomatico {
    private val cuentas = mutableMapOf<String, CuentaBancaria>()

    // Función para registrar una nueva cuenta en el sistema
    fun registrarCuenta(user: User) {
        val numeroCuenta = UUID.randomUUID().toString().substring(0, 8)
        val nuevaCuenta = CuentaBancaria(numeroCuenta, user)
        cuentas[numeroCuenta] = nuevaCuenta
        println("¡Cuenta creada exitosamente! Tu número de cuenta es: $numeroCuenta")
    }

    // Función para autenticar a un User y operar el cajero
    fun operarCajero(numeroCuenta: String, contrasena: String): Boolean {
        val cuenta = cuentas[numeroCuenta]

        if (cuenta != null && cuenta.titular.contrasena == contrasena) {
            println("Bienvenido, ${cuenta.titular.nombre}.")

            // Menú de operaciones
            while (true) {
                println("\nSeleccione una operación:")
                println("1. Consultar Saldo")
                println("2. Realizar Depósito")
                println("3. Realizar Retiro")
                println("4. Salir")

                val opcion = readLine()?.toIntOrNull()

                when (opcion) {
                    1 -> cuenta.consultarSaldo()
                    2 -> {
                        println("Ingrese el monto a depositar:")
                        val montoDeposito = readLine()?.toDoubleOrNull()
                        if (montoDeposito != null && montoDeposito > 0) {
                            cuenta.realizarDeposito(montoDeposito)
                        } else {
                            println("Error: Ingrese un monto válido.")
                        }
                    }
                    3 -> {
                        println("Ingrese el monto a retirar:")
                        val montoRetiro = readLine()?.toDoubleOrNull()
                        if (montoRetiro != null && montoRetiro > 0) {
                            cuenta.realizarRetiro(montoRetiro)
                        } else {
                            println("Error: Ingrese un monto válido.")
                        }
                    }
                    4 -> {
                        println("Gracias por utilizar nuestro cajero automático.")
                        return true
                    }
                    else -> println("Opción no válida. Inténtelo nuevamente.")
                }
            }
        } else {
            println("Error: Autenticación fallida o número de cuenta no válido.")
            return false
        }
    }
}

fun main() {
    val cajero = CajeroAutomatico()

    // Registrar Users y crear cuentas
    val User1 = User("Juan", "ID123", "contrasena1")
    val User2 = User("Maria", "ID456", "contrasena2")

    cajero.registrarCuenta(User1)
    cajero.registrarCuenta(User2)

    // Simular inicio de sesión y operaciones en el cajero automático
    println("\nInicia sesión para realizar operaciones:")
    println("Ingrese su número de cuenta:")
    val numeroCuenta = readLine()

    // Verificar el número de cuenta y solicitar la contraseña
    if (numeroCuenta != null) {
        println("Ingrese su contraseña:")
        val contrasena = readLine()

        // Intentar operar el cajero después de la autenticación
        if (contrasena != null && cajero.operarCajero(numeroCuenta, contrasena)) {
            // Operaciones adicionales después de la autenticación
        } else {
            println("Error: Autenticación fallida.")
        }
    } else {
        println("Error: Número de cuenta no válido.")
    }
}
