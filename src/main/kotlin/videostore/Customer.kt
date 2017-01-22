package videostore

data class Customer(val name: String) {

    val rentals: Set<Rental> = emptySet()

    fun statement(): String {
        var totalAmount: Double = 0.0
        var frequentRenterPoints: Int = 0
        var result: String = "Rental Record for $name\n"
        for (rental in rentals) {
            var thisAmount: Double = 0.0
            when (rental.movie.priceCode) {
                Movie.REGULAR -> {
                    thisAmount += 2
                    if (rental.daysRented > 2) {
                        thisAmount += (rental.daysRented - 2) * 1.5
                    }
                }
                Movie.NEW_RELEASE ->
                    thisAmount += rental.daysRented * 3
                Movie.CHILDRENS -> {
                    thisAmount += 1.5
                    if (rental.daysRented > 3) {
                        thisAmount += (rental.daysRented - 3) * 1.5
                    }
                }
            }
            frequentRenterPoints++
            if (rental.movie.priceCode == Movie.NEW_RELEASE && rental.daysRented > 1) {
                frequentRenterPoints++
            }
            result += "\t${rental.movie.title}\t$thisAmount\n"
            totalAmount += thisAmount
        }
        result += "Amount owed is $totalAmount\n"
        result += "You earned $frequentRenterPoints frequent renter points"
        return result
    }
}
