package videostore

import java.math.BigDecimal

/**
 * Customer.
 *
 * @property name Customer name.
 * @constructor Creates customer.
 */
data class Customer(val name: String) {

    private val rentals: MutableSet<Rental> = mutableSetOf()

    /**
     * Adds the specified [Rental] to `this` customer's [Rental]s.
     * @return `true` if the specified [Rental] has been added to `this` customer's [Rental]s.
     *         `false` if the specified [Rental] already exists in `this` customer's [Rental]s.
     */
    fun addRental(rental: Rental): Boolean = rentals.add(rental)

    /**
     * Returns the statement for `this` customer as a [String].
     * @return the statement for `this` customer as a [String].
     */
    fun statement(): String {
        return """
            |Rental Record for $name
            |${rentals.joinToString(separator = "\n") { "\t${it.movie.title}\t${it.cost()}" }}
            |Amount owed is ${cost()}
            |You earned ${frequentRenterPoints()} frequent renter points
            """.trimMargin()
    }

    /**
     * Returns the [Cost] of `this` customer's [Rental]s.
     * @return [Cost] of `this` customer's [Rental]s.
     */
    private fun cost(): Cost = rentals.map(Rental::cost).fold(Cost(BigDecimal("0.00")), Cost::plus)

    /**
     * Returns the frequent renter [Points] for `this` customer.
     * @return frequent renter [Points] for `this` customer.
     */
    private fun frequentRenterPoints(): Points = rentals.map(Rental::points).fold(Points(0), Points::plus)
}
