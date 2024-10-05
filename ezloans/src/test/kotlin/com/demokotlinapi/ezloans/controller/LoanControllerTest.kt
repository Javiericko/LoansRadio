package com.demokotlinapi.ezloans.controller

import com.demokotlinapi.ezloans.models.Loan
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class LoanControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl: String = "/api/loans"

    @Nested
    @DisplayName("GET /api/loans")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetLoans {

        @Test
        fun `should return all loans`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].idNumber") { value("1234")
                    }
                }
        }

    }

    @Nested
    @DisplayName("GET /api/loans/{idNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetLoan {

        @Test
        fun `should retrieve the loan with the given id number`() {
            // given
            val idNumber = "1234"

            // when/then
            mockMvc.get("$baseUrl/$idNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.amount") { value(1000.00) }
                    jsonPath("$.length") { value(36)
                    }
                }
        }

        @Test
        fun `should return NOT FOUND if the id number does not exist`() {
            // given
            val idNumber = "does_not_exist"

            // when/then
            mockMvc.get("$baseUrl/$idNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }

        }
    }

    @Nested
    @DisplayName("POST /api/loans")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewLoan {

        @Test
        fun `should add the new loan`() {
            // given
            val newLoan = Loan("acc123", 300.00, 2.00, 6, 25.00)

            // when
            val performPost = mockMvc.post(baseUrl)   {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newLoan)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType(MediaType.APPLICATION_JSON))
                        json(objectMapper.writeValueAsString(newLoan))
                    }
                }

            mockMvc.get("$baseUrl/${newLoan.idNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(newLoan)) } }
        }

        @Test
        fun `should return BAD REQUEST if loan already exists`() {
            // given
            val invalidLoan = Loan("1234", 300.00, 2.00, 6, 25.00)

            // when
            val performPost = mockMvc.post(baseUrl)   {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidLoan)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }
    }

    @Nested
    @DisplayName("PATCH /api/loans")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingLoan {

        @Test
        fun `should update an existing loan`() {
            // given
            val updatedLoan = Loan("momoneymoproblems", 1000000.00, 20.5, 36, 5435.00)

            // when
            val performPatch = mockMvc.patch(baseUrl)   {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedLoan)
            }

            // then
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType(MediaType.APPLICATION_JSON))
                        json(objectMapper.writeValueAsString(updatedLoan))
                    }
                }

            mockMvc.get("$baseUrl/${updatedLoan.idNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedLoan)) } }
        }

        @Test
        fun `should return BAD REQUEST if no loan with given id number exists`() {
            // given
            val invalidLoan = Loan("not_a_loan", 0.00,0.00,0,0.00)

            // when
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidLoan)
            }

            // then
            performPatch
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}
