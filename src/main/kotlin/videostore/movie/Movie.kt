package videostore.movie

import videostore.Cost
import videostore.Points

/**
 * Movie.
 *
 * Cannot be directly instantiated. Must be subclassed by specific movie types.
 *
 * @property title Title of the movie.
 * @constructor Creates a movie.
 */
abstract class Movie(open val title: String) {

    /**
     * Returns the [Cost] for the specified days rented.
     * @return [Cost] for the specified days rented.
     */
    abstract fun cost(daysRented: Int): Cost

    /**
     * Returns the [Points] for the specified days rented.
     * @return [Points] for the specified days rented.
     */
    open fun points(daysRented: Int): Points {
        validateDaysRented(daysRented)
        return Points(1)
    }

    /**
     * Validates the specified days rented to ensure it is >= 1.
     * @param daysRented Days rented to validate.
     */
    fun validateDaysRented(daysRented: Int): Unit = require(daysRented >= 1, { "Days rented must be >= 1" })
}
