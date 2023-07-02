fun main() {
    val contas = mutableListOf<Conta>()
    val nome = listOf("José", "Alonso", "Fortnite", "Amogus", "When the when")

    for (i in nome.iterator()) {
        val randomSaldo = (500 .. 1000).shuffled().last().toDouble()
        val randomAgencia = (100..500).shuffled().last()
        contas.add(Conta(i, randomSaldo, "123241", randomAgencia))
    }

    contas.sortedBy { it.saldo }.imprimirSaldo()
    println()
    contas.sortedBy { it.cliente }.imprimirCliente()
}

fun List<Conta>.imprimirCliente() {
    forEach { println(it.cliente) }
}

fun List<Conta>.imprimirSaldo() {
    forEach { println(it.saldo) }
}