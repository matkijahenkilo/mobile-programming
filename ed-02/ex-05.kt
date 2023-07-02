import java.math.BigDecimal

fun main() {
    //haja toBigDecimal()
    val coins = listOf(
        1.0.toBigDecimal(),
        .50.toBigDecimal(),
        .25.toBigDecimal(),
        .10.toBigDecimal(),
        .05.toBigDecimal(),
        .01.toBigDecimal()
    )

    println("Digite um valor monetario para receber moedas em troca:")
    val value = readln().replace(',', '.').toBigDecimal()

    println(giveExchange(coins, value))
}

fun giveExchange(coins: List<BigDecimal>, money: BigDecimal): String {
    val coinsQuantity = mutableListOf(0,0,0,0,0,0)
    var result = ""

    var value = money
    for (i in coins.indices) {
        while (value > 0.0.toBigDecimal()) {
            if (value - coins[i] >= 0.0.toBigDecimal()) {
                value -= coins[i]
                coinsQuantity[i]++
            } else {
                break
            }
        }
        result = result + coins[i].toString() + " * " + coinsQuantity[i].toString() + '\n'
    }

    return result
}