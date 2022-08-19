package com.hhyhhy.ledger.controller

import com.hhyhhy.ledger.api.request.NameRequest
import com.hhyhhy.ledger.api.response.Response
import com.hhyhhy.ledger.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bill")
class BillController @Autowired constructor(val billService: BillService) {
    @GetMapping("/list")
    fun list() = Response(billService.allThin())

    @PostMapping("")
    fun create(@RequestBody request: NameRequest) = Response(billService.create(request.name))
}