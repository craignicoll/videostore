package videostore

import spock.lang.Specification
import spock.lang.Unroll
import videostore.movie.Movie

class CustomerSpec extends Specification {

    private static final Cost MOVIE_COST = new Cost(new BigDecimal("1.00"))

    private static final Points MOVIE_POINTS = new Points(5)

    private static final String CUSTOMER_NAME = "CUSTOMER_NAME"

    private static final String MOVIE_TITLE_1 = "MOVIE_TITLE_1"

    private static final String MOVIE_TITLE_2 = "MOVIE_TITLE_2"

    private static final TestMovie MOVIE_1 = new TestMovie(MOVIE_TITLE_1)

    private static final TestMovie MOVIE_2 = new TestMovie(MOVIE_TITLE_2)

    private static final int RENTAL_DAYS_1 = 3

    private static final int RENTAL_DAYS_2 = 1

    private static final Rental RENTAL_1 = new Rental(MOVIE_1, RENTAL_DAYS_1)

    private static final Rental RENTAL_2 = new Rental(MOVIE_2, RENTAL_DAYS_2)

    @Unroll('Statement for #rentals.size rentals should be correct')
    'Calculate statement'() {
        given: 'a customer'
        Customer customer = new Customer(CUSTOMER_NAME)

        and: 'with #rentals.size rentals'
        rentals.each {
            customer.addRental(it)
        }

        when: 'calculating the statement'
        String statement = customer.statement()

        then: 'the statement should be correct'
        statement == expectedStatement

        where:
        rentals              | expectedStatement
        []                   | """|Rental Record for ${CUSTOMER_NAME}
                                |
                                |Amount owed is 0.00
                                |You earned 0 frequent renter points""".stripMargin()
        [RENTAL_1]           | """|Rental Record for ${CUSTOMER_NAME}
                                |${'\t'}${MOVIE_TITLE_1}${'\t'}${MOVIE_COST}
                                |Amount owed is ${MOVIE_COST}
                                |You earned ${MOVIE_POINTS} frequent renter points""".stripMargin()
        [RENTAL_1, RENTAL_2] | """|Rental Record for ${CUSTOMER_NAME}
                                |${'\t'}${MOVIE_TITLE_1}${'\t'}${MOVIE_COST}
                                |${'\t'}${MOVIE_TITLE_2}${'\t'}${MOVIE_COST}
                                |Amount owed is ${MOVIE_COST.times(new Cost(new BigDecimal("2.00")))}
                                |You earned ${MOVIE_POINTS.times(new Points(2))} frequent renter points""".stripMargin()
    }

    private static class TestMovie extends Movie {

        TestMovie(String title) {
            super(title)
        }

        @Override
        Cost cost(int daysRented) {
            return MOVIE_COST
        }

        @Override
        Points points(int daysRented) {
            return MOVIE_POINTS
        }
    }
}
