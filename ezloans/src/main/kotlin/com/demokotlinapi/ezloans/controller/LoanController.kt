package com.demokotlinapi.ezloans.controller

import com.demokotlinapi.ezloans.models.Loan
import com.demokotlinapi.ezloans.service.LoanService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/loans")
class LoanController(private val service: LoanService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getLoans(): Collection<Loan> = service.getLoans()

    @GetMapping("/{idNumber}")
    fun getLoan(@PathVariable idNumber: String): Loan = service.getLoan(idNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addLoan(@RequestBody loan: Loan): Loan = service.addLoan(loan)

    @PatchMapping
    fun updateLoan(@RequestBody loan: Loan): Loan = service.updateLoan(loan)
}