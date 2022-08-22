package com.hhyhhy.ledger.controller

import com.hhyhhy.ledger.api.response.Response
import com.hhyhhy.ledger.model.Record
import com.hhyhhy.ledger.service.BillService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CreateRecordRequest(
    val billId: String,
    val name: String,
    val date: String,
    val expense: Boolean,
    val amountFen: Int,
    val creatorId: String,
    val consumerIds: List<String>
) {
    fun toRecord(): Record {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return Record(
            name = name,
            date = LocalDate.parse(date, formatter),
            expense = expense,
            amountFen = amountFen,
            creatorId = ObjectId(creatorId),
            consumerIds = consumerIds.map { ObjectId(it) }
        )
    }
}

@RestController
@RequestMapping("/record")
class RecordController @Autowired constructor(val billService: BillService) {
    @PostMapping("")
    fun create(@RequestBody request: CreateRecordRequest): Response<String> {
        val record = request.toRecord()
        billService.pushRecord(request.billId, record)
        return Response(record.id.toHexString())
    }
}