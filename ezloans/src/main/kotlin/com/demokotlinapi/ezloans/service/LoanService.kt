package com.demokotlinapi.ezloans.service

import com.demokotlinapi.ezloans.datasource.LoanDataSource
import com.demokotlinapi.ezloans.models.Loan
import org.springframework.stereotype.Service

@Service
class LoanService(val dataSource: LoanDataSource) {

    fun getLoans(): Collection<Loan> = dataSource.retrieveLoans()
    fun getLoan(idNumber: String): Loan = dataSource.retrieveLoan(idNumber)
    fun addLoan(loan: Loan): Loan = dataSource.createLoan(loan)
    fun updateLoan(loan: Loan): Loan = dataSource.updateLoan(loan)

}