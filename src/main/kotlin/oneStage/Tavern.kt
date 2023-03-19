package oneStage

import java.io.File

const val TAVERNA_NAME = "Taernyl's Folly"
const val TAVERNA_HEADER = "*** Welcome $TAVERNA_NAME ***"
const val TAVERNA_SIZE = TAVERNA_HEADER.length

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
//    .reader()
//    .readLines()
    .split("\n")

val patronGold = mutableMapOf<String, Double>()

fun main() {
    println(TAVERNA_HEADER)
    printMenu()

    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed house ago")
    }

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var oderCount = 0
    while (oderCount <= 9) {
        placeOrder(
            uniquePatrons.shuffled().first(),
            menuList.shuffled().first())
        oderCount++
    }

    displayPatronBalances()

//    oneStage.getMenuList.forEachIndexed { index, data ->
//        println("$index: $data")
//    }

//    val isTavernOpen = true
//    val isClosingTime = false
//    while (isTavernOpen == true) {
//        if (isClosingTime) {
//            break
//        }
//        println("Havin a grand old time!")
//        break
//    }

//    println(oneStage.TAVERNA_HEADER)
//    println(oneStage.printMenu())

}

fun printMenu() {
    menuList.forEach {
        var name = it.dropWhile { char -> char != ',' }.drop(1).takeWhile { char -> char != ',' }
        name = name.replaceFirstChar { char -> char.uppercase() }
        val price = it.takeLastWhile { char -> char != ',' }
        val prefix = "".padStart(TAVERNA_SIZE - name.length - price.length,'.')
        val itemMenu = name.plus(prefix).plus(price)

        var nameType = it.takeWhile { char -> char != ',' }
        nameType = "~[".plus(nameType).plus("]~")
        nameType.padStart((TAVERNA_SIZE - nameType.length) / 2, ' ' )
        val one = " ".padStart((TAVERNA_SIZE - nameType.length) / 2, ' ' )
        val two = one
        val three = one.plus(nameType).plus(two)

        println("$three \n$itemMenu")

    }
}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERNA_NAME.indexOf('\'')
    val tavernMaster = TAVERNA_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speak with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when (it.value) {
            "a", "A" -> "4"
            "e", "E" -> "3"
            "i", "I" -> "1"
            "o", "O" -> "0"
            "u", "U" -> "|_|"
            else -> it.value
        }

}
