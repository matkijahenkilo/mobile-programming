class ContaPoupanca(
    cliente: String,
    saldo: Double,
    numero: String,
    agencia: Int,
): Conta(cliente, saldo, numero, agencia) {
    private fun aplicarJuros(valor: Double): Double {
        return valor * 1.3
    }

    override fun depositar(valor: Double) {
        super.saldo += aplicarJuros(valor)
    }
}