fun main() {
    // Solicitar al usuario el salario bruto y el porcentaje de impuestos
    print("Ingrese su salario mensual bruto: ")
    val salarioBruto = readLine()?.toDoubleOrNull() ?: 0.0

    print("Ingrese el porcentaje de impuestos que debe pagar por mes: ")
    val porcentajeImpuestos = readLine()?.toDoubleOrNull() ?: 0.0

    // Calcular el salario neto y el monto de impuestos mensual
    val impuestosMensual = salarioBruto * (porcentajeImpuestos / 100)
    val salarioNetoMensual = salarioBruto - impuestosMensual

    // Calcular el salario neto y el monto de impuestos anual
    val salarioNetoAnual = salarioNetoMensual * 12
    val impuestosAnual = impuestosMensual * 12

    // Imprimir en consola los resultados con un máximo de dos posiciones decimales
    println("Salario neto mensual: \$${"%.2f".format(salarioNetoMensual)}")
    println("Impuestos mensuales: \$${"%.2f".format(impuestosMensual)}")
    println("Salario neto anual: \$${"%.2f".format(salarioNetoAnual)}")
    println("Impuestos anuales: \$${"%.2f".format(impuestosAnual)}")
}
