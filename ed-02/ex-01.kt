fun main() {
    println("Input a number to calculate its factorial:")
    val number = readln().toLong()
    println("${calculateFactorial(number)}")
}

fun calculateFactorial(number: Long): Long {
    var result: Long = 1
    for (i: Long in number downTo 1) {
        result *= i
    }
    return result
}