package com.hhyhhy.ledger.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class Bill(val id: ObjectId = ObjectId.get(), var name: String, var records: List<Record> = emptyList())

data class Record(
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val date: LocalDate,
    val expense: Boolean,
    val amountFen: Int,
    val creatorId: ObjectId,
    val consumerIds: List<ObjectId>
)