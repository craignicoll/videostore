package videostore.movie

import videostore.Cost
import java.math.BigDecimal

/**
 * Children's Movie.
 *
 * @property title Title of the children's movie.
 * @constructor Creates a children's movie.
 */
class ChildrensMovie(override val title: String) : Movie(title) {

    /**
     * Constants for children's movies.
     */
    private companion object ChildrensMovie {
        val DEFAULT_COST: Cost = Cost(BigDecimal("1.50"))
    }

    override fun cost(daysRented: Int): Cost {
        validateDaysRented(daysRented)
        return if (daysRented > 3) {
            DEFAULT_COST.plus(Cost(BigDecimal.valueOf(daysRented - 3.0).times(BigDecimal.valueOf(1.5))))
        } else {
            DEFAULT_COST
        }
    }
}
