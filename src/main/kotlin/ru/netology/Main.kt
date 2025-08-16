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
            val trasferFee = calcFee(CardType.MASTERCARD, transferSumPerMonths, transferSum)
            println("Комиссия за перевод: $trasferFee рублей")

            transferSumPerDay += transferSum
            transferSumPerMonths += transferSumPerDay
        }

    }
}

fun calcFee(cardType: CardType = CardType.RUSWORLD, sumPerMonth: Double = 0.0, sum: Double): Int {

    val fee = when (cardType) {
        CardType.RUSWORLD -> sum * FEE_RUSWORLD_MIN_RATE
        CardType.MASTERCARD -> calcFeeMastercard(sum, sumPerMonth)
        CardType.VISA ->
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

fun calcFeeMastercard(sum: Double, sumPerMonth: Double = 0.0): Double = when {
    sumPerMonth > FEE_MSCARD_LIMIT -> sum * FEE_MSCARD_OVER_RATE + FEE_MSCARD_OVER_MIN_SUM
    sum + sumPerMonth > FEE_MSCARD_LIMIT ->
        (sum + sumPerMonth - FEE_MSCARD_LIMIT) * FEE_MSCARD_OVER_RATE + FEE_MSCARD_OVER_MIN_SUM

    else -> 0.0
}