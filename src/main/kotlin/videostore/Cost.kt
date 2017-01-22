package videostore

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Cost.
 *
 * @property value Value to use.
 * @constructor Creates cost.
 */
data class Cost(private val value: BigDecimal) {

    init {
        validateValue(value)
    }

    /**
     * Adds `this` to the specified [Cost].
     * @return `this` added to the specified [Cost].
     */
    fun plus(cost: Cost): Cost = Cost(cost.value.plus(value))

    /**
     * Multiplies `this` by the specified [Cost].
     * @return `this` multiplied by the specified [Cost].
     */
    fun times(cost: Cost): Cost = Cost(cost.value.times(value).setScale(2, RoundingMode.HALF_UP))

    /**
     * Returns the value of `this` as a [String].
     * @return value of `this` as a [String].
     */
    override fun toString(): String = DecimalFormat("0.00").format(value)

    /**
     * Validates the specified value to ensure it is >= 0.
     * @param value Value to validate.
     */
    private fun validateValue(value: BigDecimal): Unit = require(value >= BigDecimal.ZERO, { "Value must be >= 0" })
}
