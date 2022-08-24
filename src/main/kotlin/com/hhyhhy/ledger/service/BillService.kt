package com.hhyhhy.ledger.service

import com.hhyhhy.ledger.model.Bill
import com.hhyhhy.ledger.model.Record
import com.hhyhhy.ledger.pojo.BillDTO
import com.hhyhhy.ledger.pojo.BillThinDTO
import com.hhyhhy.ledger.pojo.RecordDTO
import com.hhyhhy.ledger.pojo.UserDTO
import com.hhyhhy.ledger.repository.BillRepository
import com.hhyhhy.ledger.repository.UserRepository
import com.hhyhhy.ledger.unwrap
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BillService @Autowired constructor(val billRepository: BillRepository, val userRepository: UserRepository) {
    fun allThin() = billRepository.findAllThin().map { BillThinDTO(it) }

    fun pushRecord(id: String, record: Record) = billRepository.pushRecord(id, record)

    fun create(name: String): String {
        val bill = Bill(name = name)
        billRepository.save(bill)
        return bill.id.toHexString()
    }

    private fun getUserDTOById(cache: MutableMap<String, UserDTO>, id: ObjectId): UserDTO {
        return cache.getOrPut(id.toHexString()) {
            val user = userRepository.findById(id.toHexString()).unwrap()
            if (user != null) UserDTO(user) else UserDTO(id.toHexString())
        }
    }

    fun findById(id: String): BillDTO? {
        val bill = billRepository.findById(id).unwrap() ?: return null
        val userCache = mutableMapOf<String, UserDTO>()
        val records = mutableListOf<RecordDTO>()

        for (record in bill.records) {
            val creatorDTO = getUserDTOById(userCache, record.creatorId)

            val consumers = mutableListOf<UserDTO>()
            for (uid in record.consumerIds) {
                val consumer = getUserDTOById(userCache, uid)
                consumers.add(consumer)
            }

            val recordDTO = RecordDTO(record, creatorDTO, consumers)
            records.add(recordDTO)
        }

        return BillDTO(bill.id.toHexString(), bill.name, records)
    }
}