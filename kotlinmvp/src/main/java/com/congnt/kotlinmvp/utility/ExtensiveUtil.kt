import java.math.BigDecimal
import java.util.regex.Pattern

fun String.toBigDecimal(): BigDecimal {
    return BigDecimal(this)
}

fun String.checkMatchPattern(pattern: Pattern): Boolean {
    return pattern.matcher(this).matches()
}