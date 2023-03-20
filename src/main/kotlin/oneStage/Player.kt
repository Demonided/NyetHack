package oneStage

import com.bignerdranch.nyethack.Fightable
import java.io.File

class Player(_name:String,
             var healthPoints: Int = 100,
             val isBlessed: Boolean,
             private val isImmortal: Boolean): Fightable {
    var name = _name
//        get() = "${field.capitalize()} of $hometown"
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }

    val hometown =  selectHometown()
    var currentPosition = Coordinate(0,0)

    private fun selectHometown () = File("data/towns")
        .readText()
        .split("\n")
        .shuffled()
        .first()

    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "OneStage.Player must have a name." })
    }


    constructor(name: String): this(name,
        healthPoints = 100,
        isBlessed = true,
        isImmortal = false) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    fun castFireball(numFireball: Int = 2) =
        println("A glass of Fireball springs into existence. Delicious! (x$numFireball)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratchers."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                "has som minor wounds."
            }

            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }

    override var heatPoints: Int = healthPoints
    override val diceCount: Int = 3
    override val diceSides: Int = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.heatPoints -= damageDealt
        return damageDealt
    }
}