package ru.netology

fun main() {

    var transferSumPerDay = 0.0
    var transferSumPerMonths = 0.0

    val transferSumList = listOf(75_000.0, 75_000.0, 30_000.0)
    for (transferSum in transferSumList) {

        println("Перевод на сумму: $transferSum рублей")
        if (isOverLimit(transferSum, transferSumPerDay, transferSumPerMonths)) {
            println("Превышение лимита. Операция отменена")
        } else {
            val trasferFee = calcFee(CARD_TYPE.MASTERCARD, transferSumPerMonths, transferSum)
            println("Комиссия за перевод: $trasferFee рублей")

            transferSumPerDay += transferSum
            transferSumPerMonths += transferSumPerDay
        }

    }
}

fun calcFee(cardType: CARD_TYPE = CARD_TYPE.RUSWORLD, sumPerMonth: Double = 0.0, sum: Double): Int {

    val fee = when (cardType) {
        CARD_TYPE.RUSWORLD -> sum * FEE_RUSWORLD_MIN_RATE
        CARD_TYPE.MASTERCARD -> calcFeeMastercard(sum, sumPerMonth)
        CARD_TYPE.VISA ->
            if (sum * FEE_VISA_MIN_RATE > FEE_VISA_MIN_SUM) sum * FEE_VISA_MIN_RATE else FEE_VISA_MIN_SUM
    }

    return fee.toInt()
}

fun isOverLimit(transferSum: Double, transferSumPerDay: Double, transferSumPerMonths: Double): Boolean {
    return when {
        transferSumPerDay + transferSum > TRANSFER_LIMIT_PER_DAY -> true
        transferSumPerMonths + transferSum > TRANSFER_LIMIT_PER_MONTHS -> true
        else -> false
    }

}

fun calcFeeMastercard(sum: Double, sumPerMonth: Double = 0.0): Double {
    if ((sum + sumPerMonth) > FEE_MSCARD_LIMIT) {
        val deltaAmount = (sum + sumPerMonth) - FEE_MSCARD_LIMIT
        return deltaAmount * FEE_MSCARD_OVER_RATE + FEE_MSCARD_OVER_MIN_SUM
    } else {
        return 0.0
    }
}