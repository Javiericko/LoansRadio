package com.demokotlinapi.ezloans.datasource.mock

import com.demokotlinapi.ezloans.datasource.LoanDataSource
import com.demokotlinapi.ezloans.models.Loan
import org.springframework.stereotype.Repository

// Marks as a Spring Boot bean: Adds it to application context and initializes it at runtime
@Repository
class MemDB : LoanDataSource{

    val loans = mutableListOf<Loan>(
        Loan("1234", 1000.00, 7.1, 36, 100.00),
        Loan("abc12", 550.00, 3.0, 12, 60.00),
        Loan("momoneymoproblems", 1000000.00, 20.5, 36, 5435.00),
    )

    override fun retrieveLoans(): Collection<Loan> = loans

    override fun retrieveLoan(idNumber: String): Loan =
        loans.firstOrNull() { it.idNumber == idNumber }
            ?: throw NoSuchElementException("Loan with id number $idNumber does not exist. Apply today!")

    override fun createLoan(loan: Loan): Loan {
        if (loans.any { it.idNumber == loan.idNumber }) {
            throw IllegalArgumentException("Good try! But a loan with id number ${loan.idNumber} already exists")
        }
        loans.add(loan)

        return loan
    }

    override fun updateLoan(loan: Loan): Loan {
        val currentLoan = loans.firstOrNull { it.idNumber == loan.idNumber }
            ?: throw NoSuchElementException("Sorry, a loan with id number ${loan.idNumber} does not exist")

        loans.remove(currentLoan)
        loans.add(loan)

        return loan
    }
}