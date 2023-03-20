package oneStage

import java.lang.IllegalStateException

fun main() {
    var swordsJuggling: Int? = null
    val isJugglingProficlient = (1..3).shuffled().last() == 3
    if (isJugglingProficlient) {
        swordsJuggling = 2
    }
    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("You juggle $swordsJuggling sword")
}

fun proficiencyCheck (swordsJuggling: Int?) {
    checkNotNull(swordsJuggling, {"Player cannot juggle swords"})
}

class UnskilledSwordJugglerException() : IllegalStateException ("Player cannot juggle swords")
