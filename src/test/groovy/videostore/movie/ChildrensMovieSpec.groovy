package videostore.movie

import spock.lang.Unroll
import videostore.Cost
import videostore.Points

class ChildrensMovieSpec extends MovieSpec {

    private static final Cost STANDARD_COST = new Cost(new BigDecimal("1.50"))

    private static final Points ONE_POINT = new Points(1)

    @Unroll('Calculate cost for #daysRented days rented')
    'Calculate cost for days rented'() {
        given: 'a childrens movie'
        ChildrensMovie childrensMovie = new ChildrensMovie(TITLE)

        when: 'calculating the cost'
        Cost cost = childrensMovie.cost(daysRented)

        then: 'the cost should be correct'
        cost == expectedCost

        where:
        daysRented | expectedCost
        1          | STANDARD_COST
        2          | STANDARD_COST
        3          | STANDARD_COST
        4          | new Cost(new BigDecimal("3.00"))
        5          | new Cost(new BigDecimal("4.50"))
    }

    @Unroll('Calculate points for #daysRented days rented')
    'Calculate points for days rented'() {
        given: 'a childrens movie'
        ChildrensMovie childrensMovie = new ChildrensMovie(TITLE)

        when: 'calculating the points'
        Points points = childrensMovie.points(daysRented)

        then: 'the points should be correct'
        points == expectedPoints

        where:
        daysRented | expectedPoints
        1          | ONE_POINT
        2          | ONE_POINT
        3          | ONE_POINT
        4          | ONE_POINT
        5          | ONE_POINT
    }

    @Override
    Movie getMovie() {
        return new ChildrensMovie(TITLE)
    }
}
