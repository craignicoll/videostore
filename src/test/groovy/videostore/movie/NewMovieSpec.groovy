package videostore.movie

import spock.lang.Unroll
import videostore.Cost
import videostore.Points

class NewMovieSpec extends MovieSpec {

    @Unroll('Calculate cost for #daysRented days rented')
    'Calculate cost for days rented'() {
        given: 'a new movie'
        NewMovie newMovie = new NewMovie(TITLE)

        when: 'calculating the cost'
        Cost cost = newMovie.cost(daysRented)

        then: 'the cost should be correct'
        cost == expectedCost

        where:
        daysRented | expectedCost
        1          | new Cost(new BigDecimal("3.00"))
        2          | new Cost(new BigDecimal("6.00"))
        3          | new Cost(new BigDecimal("9.00"))
        4          | new Cost(new BigDecimal("12.00"))
        5          | new Cost(new BigDecimal("15.00"))
    }

    @Unroll('Calculate points for #daysRented days rented')
    'Calculate points for days rented'() {
        given: 'a new movie'
        NewMovie newMovie = new NewMovie(TITLE)

        when: 'calculating the points'
        Points points = newMovie.points(daysRented)

        then: 'the points should be correct'
        points == expectedPoints

        where:
        daysRented | expectedPoints
        1          | new Points(1)
        2          | new Points(2)
        3          | new Points(2)
        4          | new Points(2)
        5          | new Points(2)
    }

    @Override
    Movie getMovie() {
        return new NewMovie(TITLE)
    }
}
