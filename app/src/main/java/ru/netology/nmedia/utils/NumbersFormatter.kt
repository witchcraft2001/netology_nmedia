package ru.netology.nmedia.utils

class NumbersFormatter {
    fun numberToString(number: Int) : String {
        return when {
            number in 1000..1099 || number in 10_000..999_999 -> String.format("%.0fK", number / 1000.0)
            number in 1100..9999 -> String.format("%.1fK", number / 1000.0)
            number in 1_000_000..1_099_999 -> String.format("%.0fM", number / 1_000_000.0)
            number >= 1_100_000 -> String.format("%.1fM", number / 1_000_000.0)
            else -> number.toString()
        }
    }
}