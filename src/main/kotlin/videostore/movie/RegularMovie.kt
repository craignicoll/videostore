package videostore.movie

import videostore.Cost
import java.math.BigDecimal

/**
 * Regular Movie.
 *
 * @property title Title of the regular movie.
 * @constructor Creates a regular movie.
 */
class RegularMovie(override val title: String) : Movie(title) {

    /**
     * Constants for regular movies.
     */
    private companion object RegularMovie {
        val DEFAULT_COST: Cost = Cost(BigDecimal("2.00"))
    }

    override fun cost(daysRented: Int): Cost {
        validateDaysRented(daysRented)
        return if (daysRented > 2) {
            DEFAULT_COST.plus(Cost(BigDecimal.valueOf(daysRented - 2.0).times(BigDecimal.valueOf(1.5))))
        } else {
            DEFAULT_COST
        }
    }
}
