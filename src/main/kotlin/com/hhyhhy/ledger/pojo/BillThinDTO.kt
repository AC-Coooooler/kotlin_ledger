package com.hhyhhy.ledger.pojo

import com.hhyhhy.ledger.model.Bill

data class BillThinDTO(val id: String, val name: String) {
    constructor(bill: Bill): this(bill.id.toHexString(), bill.name)
}