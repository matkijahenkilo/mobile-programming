fun main() {
    println("Insira as tres notas tiradas pelo aluno")
    println("Trabalho socio-culturais (peso 2):")
    val firstNumber  = readln().toInt()
    println("Trabalho de programaçao em assemply (peso 3):")
    val secondNumber = readln().toInt()
    println("Prova (peso 5):")
    val thirdNumber  = readln().toInt()

    val peso = arrayOf(2, 3, 5)
    val nota = arrayOf(firstNumber, secondNumber, thirdNumber)
    val notaTotal = getWeightedAverage(peso, nota)

    when {
        notaTotal <= 4 -> println("Aluno foi reprovado. Nota final: $notaTotal")
        notaTotal >= 7 -> println("Aluno foi aprovado. Nota final: $notaTotal")
        else -> println("Aluno ficou de final. Nota final: $notaTotal")
    }
}

fun getWeightedAverage(weight: Array<Int>, grade: Array<Int>): Int {
    var average = 0
    for (i in weight.indices) {
        average += weight[i] * grade[i]
    }
    return average / weight.sum()
}