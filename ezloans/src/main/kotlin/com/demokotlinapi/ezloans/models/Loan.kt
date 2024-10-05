package com.demokotlinapi.ezloans.models

//    data class already implements equals, hashCode, and toString
data class Loan(
//    immutable class properties
    val idNumber: String,
//    get() = field <-- Not needed, Kotlin already generates this default getter
    val amount: Double, // would these be the best types for money amounts?
    val interestRate: Double,
    val length: Int,
    val paymentAmount: Double
) {


}