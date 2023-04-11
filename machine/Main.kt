package machine

import java.util.Scanner

var WATER_COUNT: Int = 400
var MILK_COUNT: Int = 540
var COFFEE_COUNT: Int = 120
var DISPOSABLE_CUPS: Int = 9
var MONEY_COUNT: Int = 550
const val COMMAND_BUY: String = "buy"
const val COMMAND_FILL: String = "fill"
const val COMMAND_TALE: String = "take"
const val REMAINING: String = "remaining"
const val EXIT: String = "exit"
const val ESPRESSO: String = "espresso"
const val LATTE: String = "latte"
const val CAPPUCCINO: String = "cappuccino"
const val ERROR_MESSAGE: String = "I have enough resources, making you a coffee!"

data class ErrorInfo(val errorMessage: String, val errorType: String)

class EspressoSettings {
    val water: Int = 250
    val coffee: Int = 16
    val cost: Int = 4
}

class LatteSettings {
    val water: Int = 350
    val milk: Int = 75
    val coffee: Int = 20
    val cost: Int = 7
}

class CappuccinoSettings {
    val water: Int = 200
    val milk: Int = 100
    val coffee: Int = 12
    val cost: Int = 6
}

fun addWater(value: Int) {
    WATER_COUNT += value
}

fun addMilk(value: Int) {
    MILK_COUNT += value
}

fun addCoffee(value: Int) {
    COFFEE_COUNT += value
}

fun addDisposableCups(value: Int) {
    DISPOSABLE_CUPS += value
}

fun showStatistics() {
    val text = """
The coffee machine has:
$WATER_COUNT ml of water
$MILK_COUNT ml of milk
$COFFEE_COUNT g of coffee beans
$DISPOSABLE_CUPS disposable cups
${'$'}$MONEY_COUNT of money
  """

    print(text)
}

fun fillCoffeeMachine() {
    val scanner  = Scanner(System.`in`)

    println("Write how many ml of water you want to add: ")
    addWater(scanner.nextInt())

    println("Write how many ml of milk you want to add: ")
    addMilk(scanner.nextInt())

    println("Write how many grams of coffee beans you want to add: ")
    addCoffee(scanner.nextInt())

    println("Write how many disposable cups you want to add: ")
    addDisposableCups(scanner.nextInt())
}

fun getAllMoney() {
    println("I gave you $$MONEY_COUNT")
    MONEY_COUNT = 0;
}

fun prepareCoffee(type: String): ErrorInfo {
    var errorValue: String = ""

    when (type) {
        ESPRESSO -> {
            val espressoSettings = EspressoSettings()

            if (WATER_COUNT - espressoSettings.water < 0 ||
                COFFEE_COUNT - espressoSettings.coffee < 0 ||
                DISPOSABLE_CUPS - 1 < 0) {

                if (WATER_COUNT - espressoSettings.water < 0) {
                    errorValue = "water"
                } else if (COFFEE_COUNT - espressoSettings.coffee < 0) {
                    errorValue = "coffee beans"
                } else if (DISPOSABLE_CUPS - 1 < 0) {
                    errorValue = "disposable cups"
                }

                return ErrorInfo("error", errorValue)
            } else {
                WATER_COUNT -= espressoSettings.water
                COFFEE_COUNT -= espressoSettings.coffee
                DISPOSABLE_CUPS -= 1
                MONEY_COUNT += espressoSettings.cost

                return ErrorInfo("ok", errorValue)
            }
        }
        LATTE -> {
            val latteSettings = LatteSettings()

            if (WATER_COUNT - latteSettings.water < 0 ||
                MILK_COUNT - latteSettings.milk < 0 ||
                COFFEE_COUNT - latteSettings.coffee < 0 ||
                DISPOSABLE_CUPS - 1 < 0) {

                if (WATER_COUNT - latteSettings.water < 0) {
                    errorValue = "water"
                } else if (MILK_COUNT - latteSettings.milk < 0) {
                    errorValue = "milk"
                } else if (COFFEE_COUNT - latteSettings.coffee < 0) {
                    errorValue = "coffee beans"
                } else if (DISPOSABLE_CUPS - 1 < 0) {
                    errorValue = "disposable cups"
                }

                return ErrorInfo("error", errorValue)
            } else {
                WATER_COUNT -= latteSettings.water
                MILK_COUNT -= latteSettings.milk
                COFFEE_COUNT -= latteSettings.coffee
                DISPOSABLE_CUPS -= 1
                MONEY_COUNT += latteSettings.cost

                return ErrorInfo("ok", errorValue)
            }
        }
        CAPPUCCINO -> {
            val cappuccinoSettings = CappuccinoSettings()

            if (WATER_COUNT - cappuccinoSettings.water < 0 ||
                MILK_COUNT - cappuccinoSettings.milk < 0 ||
                COFFEE_COUNT - cappuccinoSettings.coffee < 0 ||
                DISPOSABLE_CUPS - 1 < 0) {

                if (WATER_COUNT - cappuccinoSettings.water < 0) {
                    errorValue = "water"
                } else if (MILK_COUNT - cappuccinoSettings.milk < 0) {
                    errorValue = "milk"
                } else if (COFFEE_COUNT - cappuccinoSettings.coffee < 0) {
                    errorValue = "coffee beans"
                } else if (DISPOSABLE_CUPS - 1 < 0) {
                    errorValue = "disposable cups"
                }

                return ErrorInfo("error", errorValue)
            } else {
                WATER_COUNT -= cappuccinoSettings.water
                MILK_COUNT -= cappuccinoSettings.milk
                COFFEE_COUNT -= cappuccinoSettings.coffee
                DISPOSABLE_CUPS -= 1
                MONEY_COUNT += cappuccinoSettings.cost

                return ErrorInfo("ok", errorValue)
            }
        }
    }

    return ErrorInfo("ok", errorValue)
}

fun buyCoffee() {
    val scanner  = Scanner(System.`in`)

    println()
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")

    when (scanner.nextLine()) {
        "1" -> {
            val (errorMessage, errorType) = prepareCoffee(ESPRESSO)

            if (errorMessage == "error") {
                println("Sorry, not enough $errorType!")
                runCoffeeMachine()
            } else {
                runCoffeeMachine()
            }
        }
        "2" -> {
            val (errorMessage, errorType) = prepareCoffee(LATTE)

            if (errorMessage == "error") {
                println("Sorry, not enough $errorType!")
                runCoffeeMachine()
            } else {
                runCoffeeMachine()
            }
        }
        "3" -> {
            val (errorMessage, errorType) = prepareCoffee(CAPPUCCINO)

            if (errorMessage == "error") {
                println("Sorry, not enough $errorType!")
                runCoffeeMachine()
            } else {
                runCoffeeMachine()
            }
        }
        "back" -> {
            runCoffeeMachine()
        }
    }
}

fun runCoffeeMachine() {
    println()
    println("Write action (buy, fill, take, remaining, exit): ")
    val scanner  = Scanner(System.`in`)
    when (scanner.nextLine()) {
        "$COMMAND_BUY" -> {
            buyCoffee()
        }
        "$COMMAND_FILL" -> {
            fillCoffeeMachine()
            runCoffeeMachine()
        }
        "$COMMAND_TALE" -> {
            getAllMoney()
            runCoffeeMachine()
        }
        "$REMAINING" -> {
            showStatistics()
            runCoffeeMachine()
        }
        "$EXIT" -> {
            return
        }
    }
}

fun main() {
    runCoffeeMachine()
}
