package videostore

import spock.lang.Specification
import spock.lang.Unroll

class PointsSpec extends Specification {

    private static final String VALUE_ERROR_MESSAGE = "Value must be >= 0"

    @Unroll('#initialPoints + #pointsToAdd = #expectedPoints')
    'Plus should add points'() {
        given: 'points'
        Points points = initialPoints

        when: 'adding points'
        Points result = points.plus(pointsToAdd)

        then: 'the result should be correct'
        result == expectedPoints

        where:
        initialPoints    | pointsToAdd      | expectedPoints
        new Points(1)    | new Points(1000) | new Points(1001)
        new Points(5)    | new Points(100)  | new Points(105)
        new Points(10)   | new Points(10)   | new Points(20)
        new Points(100)  | new Points(5)    | new Points(105)
        new Points(1000) | new Points(1)    | new Points(1001)
    }

    @Unroll('#initialPoints * #pointsToMultiply = #expectedPoints')
    'Times should multiply points'() {
        given: 'points'
        Points points = initialPoints

        when: 'multiplying points'
        Points result = points.times(pointsToMultiply)

        then: 'the result should be correct'
        result == expectedPoints

        where:
        initialPoints    | pointsToMultiply | expectedPoints
        new Points(1)    | new Points(1000) | new Points(1000)
        new Points(5)    | new Points(100)  | new Points(500)
        new Points(10)   | new Points(10)   | new Points(100)
        new Points(100)  | new Points(5)    | new Points(500)
        new Points(1000) | new Points(1)    | new Points(1000)
    }

    @Unroll('#initialPoints toString = #expectedToString')
    'toString should use two decimal places'() {
        given: 'points'
        Points points = initialPoints

        when: 'invoking toString'
        String result = points.toString()

        then: 'the result should be correct'
        result == expectedToString

        where:
        initialPoints    | expectedToString
        new Points(1)    | "1"
        new Points(5)    | "5"
        new Points(10)   | "10"
        new Points(100)  | "100"
        new Points(1000) | "1000"
    }

    @Unroll('Throw IllegalArgumentException if value #value < 0')
    'Throw IllegalArgumentException if value < 0'() {
        given: 'a value less than 0'
        int pointsValue = value

        when: 'constructing the points'
        new Points(pointsValue)

        then: 'an IllegalArgumentException should be thrown'
        IllegalArgumentException ex = thrown()
        ex.message == VALUE_ERROR_MESSAGE

        where:
        value << [-1, -2, -3, -4, -5]
    }
}
