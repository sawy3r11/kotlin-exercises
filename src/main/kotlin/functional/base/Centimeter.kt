package functional.base.centimeter

import kotlin.math.abs

class Centimeter(val value: Double) {
    fun plus(other: Centimeter): Centimeter =
        Centimeter(value + other.value)

    fun times(other: Double): Centimeter =
        Centimeter(value * other)

    override fun toString(): String = "$value cm"
}

val Int.cm get() = Centimeter(this.toDouble())
fun Int.covertToCm() = Centimeter(this.toDouble())

fun distance(from: Centimeter, to: Centimeter): Centimeter =
    Centimeter(abs(to.value - from.value))

fun main() {
    val f1: (Centimeter, Centimeter) -> Centimeter = Centimeter::plus
    val f2: (Centimeter, Double) -> Centimeter = Centimeter::times
    val f3: (Centimeter) -> Double = Centimeter::value
    val f4: (Centimeter) -> String = Centimeter::toString
    val f5: (Centimeter) -> Centimeter = Centimeter(1.0)::plus
    val f6: (Double) -> Centimeter = Centimeter(2.0)::times
    val f7: () -> Double = Centimeter(3.0)::value
    val f8: () -> String = Centimeter(4.0)::toString
    val f9: (Int) -> Centimeter = Int::cm
    val f10: () -> Centimeter = 123::cm
    val f11: (Centimeter, Centimeter) -> Centimeter = ::distance

    val f1_example: Centimeter = f1(Centimeter(1.0), Centimeter(2.0))
    val f5_example: Centimeter = f5(Centimeter(2.0))
    val f6_example: Centimeter = f6(10.0)

    val f8_example: String = f8()
}
