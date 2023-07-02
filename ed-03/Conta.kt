open class Conta(nome: String = "", saldo: Double = 0.0, numero: String = "", agencia: Int = 0): Boleto {
    val cliente: String = nome
        get() = field
    var saldo: Double = saldo
        get() = field
        set(value) { field = value }
    private val numero: String = numero
    private val agencia: Int = agencia

    constructor(nome: String, numero: String, agencia: Int): this(nome, 0.0, numero, agencia)

    open fun depositar(valor: Double) {
        this.saldo += valor
    }

    fun retirar(valor: Double) {
        this.saldo -= valor
    }

    fun transferir(conta: Conta, valor: Double) {
        conta.saldo += valor
        this.saldo  -= valor
    }

    fun emprimirExtrato() {
        println("Cliente: ${this.cliente}\n" +
                "Agência: ${this.agencia}\n" +
                "Numero:  ${this.numero}\n" +
                "Saldo:   ${this.saldo}")
    }
}