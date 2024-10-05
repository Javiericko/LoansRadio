package com.demokotlinapi.ezloans

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class TheBestController {

    @GetMapping
    fun helloWorld(): String = "Hello, World! EzLoans are Gr-r-reat!"
}