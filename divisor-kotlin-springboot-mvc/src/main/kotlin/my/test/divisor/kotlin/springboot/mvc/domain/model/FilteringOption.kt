package my.test.divisor.kotlin.springboot.mvc.domain.model

enum class FilteringOption {

    EVEN, ODD;

    companion object {
        fun byValue(value: String?): FilteringOption? =
            values().firstOrNull { it.name.equals(value, ignoreCase = true) }
    }
}