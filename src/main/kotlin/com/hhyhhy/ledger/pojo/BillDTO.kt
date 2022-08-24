package com.hhyhhy.ledger.pojo

import com.hhyhhy.ledger.model.Record
import java.time.format.DateTimeFormatter

data class RecordDTO(
    val id: String,
    val name: String,
    val date: String,
    val expense: Boolean,
    val amountFen: Int,
    val creator: UserDTO,
    val consumers: List<UserDTO>
) {
    constructor(record: Record, creator: UserDTO, consumers: List<UserDTO>) : this(
        id = record.id.toHexString(),
        name = record.name,
        date = record.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        expense = record.expense,
        amountFen = record.amountFen,
        creator = creator,
        consumers = consumers
    )
}

data class BillDTO(val id: String, val name: String, val records: List<RecordDTO>)