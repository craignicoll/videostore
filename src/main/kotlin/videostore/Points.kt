package videostore

/**
 * Points.
 *
 * @property value Value to use.
 * @constructor Creates points.
 */
data class Points(private val value: Int) {

    init {
        validateValue(value)
    }

    /**
     * Adds `this` to the specified [Points].
     * @return `this` added to the specified [Points].
     */
    fun plus(points: Points): Points = Points(points.value.plus(value))

    /**
     * Multiplies `this` by the specified [Points].
     * @return `this` multiplied by the specified [Points].
     */
    fun times(points: Points): Points = Points(points.value.times(value))

    /**
     * Returns the value of `this` as a [String].
     * @return value of `this` as a [String].
     */
    override fun toString(): String = value.toString()

    /**
     * Validates the specified value to ensure it is >= 0.
     * @param value Value to validate.
     */
    private fun validateValue(value: Int): Unit = require(value >= 0, { "Value must be >= 0" })
}
