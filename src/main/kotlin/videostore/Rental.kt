package videostore

import videostore.movie.Movie

/**
 * Rental.
 *
 * @property movie [Movie] that has been rented.
 * @property days Number of days the rental was for.
 * @constructor Creates a rental.
 */
data class Rental(val movie: Movie, val days: Int) {

    init {
        validateDays(days)
    }

    /**
     * Returns the [Cost] of the rental.
     * @return [Cost] of the rental.
     */
    fun cost(): Cost = movie.cost(days)

    /**
     * Returns the [Points] of the rental.
     * @return [Points] of the rental.
     */
    fun points(): Points = movie.points(days)

    /**
     * Validates the specified days to ensure it is >= 1.
     * @param days Days to validate.
     */
    private fun validateDays(days: Int): Unit = require(days >= 1, { "Days must be >= 1" })
}
