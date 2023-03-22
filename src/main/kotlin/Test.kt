import kotlin.system.measureNanoTime

fun main() {
    val listAnimals = listOf("Cat", "Dog", "Wolf", "Rat", "Hare")
    val baby = listAnimals.map { nameBaby -> "\nЯ люблю $nameBaby" }
        .map { body -> "$body, и хочу завести себе ${listAnimals.shuffled().first()}" }
//    println(baby)

    val itemsOfManyColors = listOf(listOf("red apple", "green apple", "blue apple"),
        listOf("red fish", "blue fish"), listOf("yellow banana", "teal banana")
    )

    val readItems = itemsOfManyColors.flatMap { it.filter { it.contains("blue") } }
//    println(readItems)

//    val numbers = listOf(7, 4, 8, 4, 3, 22, 18, 11)
//    val primes = numbers.filter { number ->
//        (2 until  number).map { number % it }
//            .none { it == 0}
//    }
//    println(primes)

//    val employees = listOf("Denny", "Claudette", "Peter")
//    val shirtSize = listOf<String>("large", "x-large", "medium")
//    val employeesShirtSize = employees.zip(shirtSize).toMap()
//    println(employeesShirtSize)

//    val foldedValue = listOf(1, 2, 3, 4).fold(0) { accumulator, number ->
//        println("Accumulated value: $accumulator")
//        accumulator + (number * 3)
//    }
//    println("Final value: $foldedValue")

    // Расширение для Int, которое проверяет, является ли число простым
//    fun Int.isPrime(): Boolean {
//        (200 until this).map {
//            if (this % it == 0) {
//                return false // Не простое!
//            }
//        }
//        return true
//    }
//    val toList = (1..5000).toList().filter { it.isPrime() }.take(1000)
//    println(toList)

//    val listInNanos = measureNanoTime {
//        // Цепочка функций для обработки списка
//    }
//    val sequenceInNanos = measureNanoTime {
//        // Цепочка функций для обработки последовательности
//    }
//    println("список обработан за $listInNanos наносекунд ")//
//    println("последовательность обработана за $sequenceInNanos наносекунд")//

//    val gradesByStudent = mapOf("Josh" to 4.0, "Alex" to 2.0, "Jane" to 3.0)
//    println(gradesByStudent)
//    val flipValues = gradesByStudent.map { it.value }.map { it }
//    println(flipValues)

    val valuesToAdd = listOf(1,18,73,3,44,6,1,33,2,22,5,7).filter { number ->
         (number >= 5)}.map { it }
    println("Step : $valuesToAdd")
}




