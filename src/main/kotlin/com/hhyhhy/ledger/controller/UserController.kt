package com.hhyhhy.ledger.controller

import com.hhyhhy.ledger.api.request.NameRequest
import com.hhyhhy.ledger.api.response.Response
import com.hhyhhy.ledger.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(val userService: UserService) {
    @GetMapping("/{id}")
    fun find(@PathVariable id: String) = Response(userService.find(id))

    @GetMapping("/list")
    fun list() = Response(userService.all())

    @PostMapping("")
    fun create(@RequestBody request: NameRequest) = Response(userService.create(request.name))
}