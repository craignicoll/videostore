package videostore

import spock.lang.Specification
import spock.lang.Unroll

class CostSpec extends Specification {

    private static final String VALUE_ERROR_MESSAGE = "Value must be >= 0"

    @Unroll('#initialCost plus #costToAdd = #expectedCost')
    'Plus should add costs'() {
        given: 'a cost'
        Cost cost = initialCost

        when: 'adding costs'
        Cost result = cost.plus(costToAdd)

        then: 'the result should be correct'
        result == expectedCost

        where:
        initialCost                      | costToAdd                        | expectedCost
        new Cost(new BigDecimal("1.00")) | new Cost(new BigDecimal("1.00")) | new Cost(new BigDecimal("2.00"))
        new Cost(new BigDecimal("2.75")) | new Cost(new BigDecimal("2.25")) | new Cost(new BigDecimal("5.00"))
        new Cost(new BigDecimal("3.50")) | new Cost(new BigDecimal("3.50")) | new Cost(new BigDecimal("7.00"))
        new Cost(new BigDecimal("4.25")) | new Cost(new BigDecimal("4.75")) | new Cost(new BigDecimal("9.00"))
        new Cost(new BigDecimal("5.00")) | new Cost(new BigDecimal("5.00")) | new Cost(new BigDecimal("10.00"))
    }

    @Unroll('#initialCost times #costToMultiply = #expectedCost')
    'Times should multiply costs'() {
        given: 'a cost'
        Cost cost = initialCost

        when: 'multiplying costs'
        Cost result = cost.times(costToMultiply)

        then: 'the result should be correct'
        result == expectedCost

        where:
        initialCost                      | costToMultiply                   | expectedCost
        new Cost(new BigDecimal("1.00")) | new Cost(new BigDecimal("1.00")) | new Cost(new BigDecimal("1.00"))
        new Cost(new BigDecimal("2.75")) | new Cost(new BigDecimal("2.25")) | new Cost(new BigDecimal("6.19"))
        new Cost(new BigDecimal("3.50")) | new Cost(new BigDecimal("3.50")) | new Cost(new BigDecimal("12.25"))
        new Cost(new BigDecimal("4.25")) | new Cost(new BigDecimal("4.75")) | new Cost(new BigDecimal("20.19"))
        new Cost(new BigDecimal("5.00")) | new Cost(new BigDecimal("5.00")) | new Cost(new BigDecimal("25.00"))
    }

    @Unroll('#initialCost toString = #expectedToString')
    'toString should use two decimal places'() {
        given: 'a cost'
        Cost cost = initialCost

        when: 'invoking toString'
        String result = cost.toString()

        then: 'the result should be correct'
        result == expectedToString

        where:
        initialCost                        | expectedToString
        new Cost(new BigDecimal("0.00"))   | "0.00"
        new Cost(new BigDecimal("0.01"))   | "0.01"
        new Cost(new BigDecimal("0.10"))   | "0.10"
        new Cost(new BigDecimal("1.00"))   | "1.00"
        new Cost(new BigDecimal("1.99"))   | "1.99"
        new Cost(new BigDecimal("0.000"))  | "0.00"
        new Cost(new BigDecimal("0.019"))  | "0.02"
        new Cost(new BigDecimal("0.105"))  | "0.10"
        new Cost(new BigDecimal("1.0099")) | "1.01"
        new Cost(new BigDecimal("1.9901")) | "1.99"
    }

    @Unroll('Throw IllegalArgumentException if value #value < 0')
    'Throw IllegalArgumentException if value < 0'() {
        given: 'a value less than 0'
        BigDecimal costValue = new BigDecimal(value)

        when: 'constructing the cost'
        new Cost(costValue)

        then: 'an IllegalArgumentException should be thrown'
        IllegalArgumentException ex = thrown()
        ex.message == VALUE_ERROR_MESSAGE

        where:
        value << [-1, -2, -3, -4, -5]
    }
}
