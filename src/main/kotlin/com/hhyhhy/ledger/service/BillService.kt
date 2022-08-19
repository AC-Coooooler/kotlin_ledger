package com.hhyhhy.ledger.service

import com.hhyhhy.ledger.model.Bill
import com.hhyhhy.ledger.pojo.BillThinDTO
import com.hhyhhy.ledger.repository.BillRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BillService @Autowired constructor(val billRepository: BillRepository) {
    fun allThin(): List<BillThinDTO> {
        return billRepository.findAllThin().map { BillThinDTO(it) }
    }

    fun create(name: String): String {
        val bill = Bill(name = name)
        billRepository.save(bill)
        return bill.id.toHexString()
    }
}