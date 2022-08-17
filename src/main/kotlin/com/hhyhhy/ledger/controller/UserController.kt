package com.hhyhhy.ledger.controller


import com.hhyhhy.ledger.model.users
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(val database: Database) {
    @GetMapping("/list")
    fun list() = database.users.toList()

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long) = database.users.find { it.id eq id }
}