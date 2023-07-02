fun main() {
    var result = 1
    for (i in 0 .. 10) {
        if (i != 0) {
            result *= i
        }
        println("$i! = $result")
    }
}