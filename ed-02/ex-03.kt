fun main() {
    println("Input three numbers to sort them")
    println("Input the first number:")
    val firstNumber  = readln().toInt()
    println("Input the second number:")
    val secondNumber = readln().toInt()
    println("Input the third number:")
    val thirdNumber  = readln().toInt()

    for (i in getSortedNumbers(firstNumber, secondNumber, thirdNumber))
        print("$i ")
}

fun getSortedNumbers(x: Int, y: Int, z: Int): Array<Int> {
    val sorted = arrayOf(x, y, z)
    sorted.sort()
    return sorted
}