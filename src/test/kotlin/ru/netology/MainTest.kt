package ru.netology

import org.junit.Assert.*
import org.junit.Test

class MainTest {

    @Test
    fun isOverLimit_test01() {
        val transferSum =  TRANSFER_LIMIT_PER_DAY - 1
        val transferSumPerDay = 0.0
        val transferSumPerMonths = 0.0

        val result = isOverLimit(transferSum, transferSumPerDay, transferSumPerMonths)


        assertEquals(false, result)
    }

    @Test
    fun isOverLimit_test02() {
        val transferSum =  100.0
        val transferSumPerDay = TRANSFER_LIMIT_PER_DAY
        val transferSumPerMonths = 0.0

        val result = isOverLimit(transferSum, transferSumPerDay, transferSumPerMonths)


        assertEquals(true, result)
    }

    @Test
    fun isOverLimit_test03() {
        val transferSum =  100.0
        val transferSumPerDay = TRANSFER_LIMIT_PER_DAY
        val transferSumPerMonths = TRANSFER_LIMIT_PER_MONTHS

        val result = isOverLimit(transferSum, transferSumPerDay, transferSumPerMonths)


        assertEquals(true, result)
    }

    @Test
    fun isOverLimit_test04() {
        val transferSum =  100.0
        val transferSumPerDay = 0.0
        val transferSumPerMonths = TRANSFER_LIMIT_PER_MONTHS

        val result = isOverLimit(transferSum, transferSumPerDay, transferSumPerMonths)


        assertEquals(true, result)
    }


    @Test
    fun calcFeeMastercard_test01() {
        val sum = 0.0
        val sumPerMonth = 0.0

        val result = calcFeeMastercard(sum, sumPerMonth)

        //assert
        assertEquals(0.0, 0.0, 0.0)
    }

    @Test
    fun calcFeeMastercard_test02() {
        val sum = 0.0
        val sumPerMonth = FEE_MSCARD_LIMIT +1

        val result = calcFeeMastercard(sum, sumPerMonth)

        //assert
        assertEquals(20.0, result, 0.0)
    }

    @Test
    fun calcFeeMastercard_test03() {
        val sum = FEE_MSCARD_OVER_MIN_SUM * 100
        val sumPerMonth = FEE_MSCARD_LIMIT

        val result = calcFeeMastercard(sum, sumPerMonth)

        //assert
        assertEquals(32.0, result, 0.0)
    }

    @Test
    fun calcFee_MASTERCARD() {
        // arrange
        val cardType = CardType.MASTERCARD
        val sumPerMonth  = 0.0
        val sum = 150_000.0

        // act
        val result = calcFee(cardType, sumPerMonth, sum)

        //assert
        assertEquals(470, result)
    }

    @Test
    fun calcFee_RUSWORLD() {
        // arrange
        val cardType = CardType.RUSWORLD
        val sumPerMonth  = 0.0
        val sum = 150_000.0

        // act
        val result = calcFee(cardType, sumPerMonth, sum)

        //assert
        assertEquals(0, result)
    }

    @Test
    fun calcFee_VISA() {
        // arrange
        val cardType = CardType.VISA
        val sumPerMonth  = 0.0
        val sum = 200_000.0

        // act
        val result = calcFee(cardType, sumPerMonth, sum)

        //assert
        assertEquals(1500, result)
    }

    @Test
    fun calcFeeVisaCheckMinSum() {
        // arrange
        val cardType = CardType.VISA
        val sumPerMonth  = 0.0
        val sum = 10.0

        // act
        val result = calcFee(cardType, sumPerMonth, sum)

        //assert
        assertEquals(35, result)
    }


}