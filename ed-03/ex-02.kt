fun main() {
    val conta1 = Conta("lol", 1000.00, "97728922", 35843)
    val conta2 = Conta("lal", 1000.00, "32984292", 321847)

    println(conta1.saldo)
    conta1.depositar(150.00)
    println(conta1.saldo)
    conta1.retirar(50.00)
    println(conta1.saldo)
    conta1.transferir(conta2, 500.00)
    println()
    println(conta1.saldo)
    println(conta2.saldo)

    println()
    conta1.emprimirExtrato()
    println()
    conta2.emprimirExtrato()
}