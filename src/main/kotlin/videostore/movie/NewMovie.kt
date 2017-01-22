package videostore.movie

import videostore.Cost
import videostore.Points
import java.math.BigDecimal

/**
 * New Movie.
 *
 * @property title Title of the new movie.
 * @constructor Creates a new movie.
 */
class NewMovie(override val title: String) : Movie(title) {

    /**
     * Constants for new movies.
     */
    private companion object NewMovie {
        val DEFAULT_POINTS: Points = Points(1)
    }

    override fun cost(daysRented: Int): Cost {
        validateDaysRented(daysRented)
        return Cost(BigDecimal(daysRented).times(BigDecimal("3.00")))
    }

    override fun points(daysRented: Int): Points {
        validateDaysRented(daysRented)
        return if (daysRented > 1) {
            DEFAULT_POINTS.plus(Points(1))
        } else {
            DEFAULT_POINTS
        }
    }
}
