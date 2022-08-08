package com.hhyhhy.ledger.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Home {
    @GetMapping("/")
    fun home() = "hello"
}