package ru.kuznetsoviv.features.lambda

/**
 * Пример объвления лямбда выражений.
 */
fun lambdas() {
    val addFive = { x: Int -> x + 5 }
    println("Pass 6 to addFive: ${addFive(6)}")

    val addInts = { number1: Int, number2: Int -> Int }
    println("Sum numbers 5 and 3 ${addInts.invoke(5, 3)}")

    val mulInts: (Int, Int) -> Int = { number1: Int, number2: Int -> number1 * number2 }
    println("Mul numbers 2 and 6 ${mulInts(2, 6)}")

    val addSeven: (Int) -> Int = { it + 7 }
    println("Pass 9 to addSeven: ${addSeven(9)}")

    val myLambda: () -> Unit = { println("Hi!") }
    myLambda()

}

/**
 * Пример передачи лямбда выражения в функцию.
 * Если лямбда выражение передано последним параметром, то вызывать функцию можно с особым синтаксическим сахором.
 */
fun convert(x: Double, converter: (Double) -> Double): Double {
    val result = converter(x)
    println("x is converted to $result")
    return result
}

/**
 * Пример возвращения лямбда параметра.
 */
fun getConversionLambda(str: String): (Double) -> Double {
    when (str) {
        "CentigradeToFahrenheit" -> {
            return { it * 1.8 + 32 }
        }
        "KgsToPounds" -> {
            return { it * 2.204623 }
        }
        "PoundsToUSTons" -> {
            return { it / 2000.0 }
        }
        else -> {
            return { it }
        }
    }
}

/**
 * Алиас для лямбда выражений (псевдоним типа).
 * С помощью такого синтаксиса можно определять алиас для любых типов.
 * typealias DuckArray = Array<Duck>
 */
typealias DoubleConversion = (Double) -> Double

/**
 * Пример объединения двух лямбда функций в одну лямбду.
 */
fun combine(lambda1: DoubleConversion, lambda2: DoubleConversion): DoubleConversion {
    return { x: Double -> lambda1(lambda2(x)) }
}

fun main() {
    lambdas()
    convert(20.0) { it * 2 }
    getConversionLambda("CentigradeToFahrenheit")(33.3)
    val kgsToPounds = { x: Double -> x * 2.204623 }
    val poundsToUSTons = { x: Double -> x / 2000.0 }
    val kgsToUSTons = combine(kgsToPounds, poundsToUSTons)
    println(kgsToUSTons(55.3))
}