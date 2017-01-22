package videostore.movie

import spock.lang.Specification
import spock.lang.Unroll

abstract class MovieSpec extends Specification {

    static final String TITLE = "TITLE"

    private static final String DAYS_RENTED_ERROR_MESSAGE = "Days rented must be >= 1"

    @Unroll('Throw IllegalArgumentException if calculating cost for #daysRented days rented')
    'Throw IllegalArgumentException if calculating cost for less than 1 days rented'() {
        given: 'a childrens movie'
        Movie movie = getMovie()

        when: 'calculating the cost'
        movie.cost(daysRented)

        then: 'an IllegalArgumentException should be thrown'
        IllegalArgumentException ex = thrown()
        ex.message == DAYS_RENTED_ERROR_MESSAGE

        where:
        daysRented << [0, -1]
    }

    @Unroll('Throw IllegalArgumentException if calculating points for #daysRented days rented')
    'Throw IllegalArgumentException if calculating points for less than 1 days rented'() {
        given: 'a childrens movie'
        Movie movie = getMovie()

        when: 'calculating the points'
        movie.points(daysRented)

        then: 'an IllegalArgumentException should be thrown'
        IllegalArgumentException ex = thrown()
        ex.message == DAYS_RENTED_ERROR_MESSAGE

        where:
        daysRented << [0, -1]
    }

    abstract Movie getMovie()

}
