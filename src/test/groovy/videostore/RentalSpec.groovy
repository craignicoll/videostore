package videostore

import spock.lang.Specification
import spock.lang.Unroll
import videostore.movie.Movie

class RentalSpec extends Specification {

    private static final int DAYS = 1

    private static final Cost COST = new Cost(new BigDecimal("1.00"))

    private static final Points POINTS = new Points(1)

    private static final String DAYS_ERROR_MESSAGE = "Days must be >= 1"

    private Movie movie = Mock(Movie)

    def 'Calculate cost'() {
        given: 'a rental'
        Rental rental = new Rental(movie, DAYS)

        when: 'calculating the cost'
        Cost cost = rental.cost()

        then: 'the cost should be correct'
        cost == COST
        1 * movie.cost(DAYS) >> COST
    }

    def 'Calculate points'() {
        given: 'a rental'
        Rental rental = new Rental(movie, DAYS)

        when: 'calculating the points'
        Points points = rental.points()

        then: 'the points should be correct'
        points == POINTS
        1 * movie.points(DAYS) >> POINTS
    }

    @Unroll('Throw IllegalArgumentException if days #days < 1')
    'Throw IllegalArgumentException if days < 1'() {
        given: 'days less than 1'
        int rentalDays = days

        when: 'constructing the rental'
        new Rental(movie, rentalDays)

        then: 'an IllegalArgumentException should be thrown'
        IllegalArgumentException ex = thrown()
        ex.message == DAYS_ERROR_MESSAGE

        where:
        days << [0, -1, -2, -3, -4]
    }
}
