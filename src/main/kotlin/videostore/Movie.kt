package videostore

data class Movie(val title: String, val priceCode: Int) {
    companion object Type {
        const val CHILDRENS: Int = 2
        const val REGULAR: Int = 0
        const val NEW_RELEASE: Int = 1
    }
}
