package com.hhyhhy.ledger.pojo

data class RecordDTO(
    val id: String,
    val name: String,
    val date: String,
    val expense: Boolean,
    val amountFen: Int,
    val creator: UserDTO,
    val consumers: List<UserDTO>
)

data class BillDTO(val id: String, val name: String, val records: List<RecordDTO>)