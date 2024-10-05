package com.demokotlinapi.ezloans.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MemDBTest {

    private val mockDataSource = MemDB()

    @Test
    fun `should provide some mock loan data`() {
        // when
        val loans = mockDataSource.retrieveLoans()

        // then
        assertThat(loans.size).isEqualTo(3)

        assertThat(loans).allMatch { it.idNumber.isNotBlank() }
        assertThat(loans).allMatch { it.amount != 0.0 }
        assertThat(loans).allMatch { it.interestRate != 0.00 }

    }

}