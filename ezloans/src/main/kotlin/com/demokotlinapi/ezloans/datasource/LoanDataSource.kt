package com.demokotlinapi.ezloans.datasource

import com.demokotlinapi.ezloans.models.Loan

interface LoanDataSource {

    fun retrieveLoans(): Collection<Loan>
    fun retrieveLoan(idNumber: String): Loan
    fun createLoan(loan: Loan): Loan
    fun updateLoan(loan: Loan): Loan
}