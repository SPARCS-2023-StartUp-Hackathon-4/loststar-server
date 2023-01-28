package sparcs.loststar.domain.store

enum class ExchangeRate(private val starPiece: Int) {
    ONE(1000),
    THREE(2800),
    FIVE(4500);

    fun starPiece(): Int = starPiece
}