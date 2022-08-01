package ru.netology.nmedia.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class NumbersFormatterTest {

    private val formatter = NumbersFormatter()

    @Test
    fun formatOneHundred() {
        //arrange
        //act
        val result = formatter.numberToString(100)
        //assert
        assertEquals("100", result)
    }

    @Test
    fun formatOneThousand() {
        //arrange
        //act
        val result = formatter.numberToString(1000)
        //assert
        assertEquals("1K", result)
    }

    @Test
    fun formatOneThousandOneHundred() {
        //arrange
        //act
        val result = formatter.numberToString(1100)
        //assert
        assertEquals("1,1K", result.replace(".", ","))
    }

    @Test
    fun formatTwoThousandsFiveHundreds() {
        //arrange
        //act
        val result = formatter.numberToString(2500)
        //assert
        assertEquals("2,5K", result.replace(".", ","))
    }

    @Test
    fun formatTenThousands() {
        //arrange
        //act
        val result = formatter.numberToString(10000)
        //assert
        assertEquals("10K", result)
    }

    @Test
    fun formatFifteenThousands() {
        //arrange
        //act
        val result = formatter.numberToString(15000)
        //assert
        assertEquals("15K", result)
    }

    @Test
    fun formatOneMillion() {
        //arrange
        //act
        val result = formatter.numberToString(1_000_000)
        //assert
        assertEquals("1M", result)
    }

    @Test
    fun formatOneMillionTwoHundredsThousands() {
        //arrange
        //act
        val result = formatter.numberToString(1_200_000)
        //assert
        assertEquals("1,2M", result.replace(".", ","))
    }

}