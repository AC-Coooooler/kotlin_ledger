package com.hhyhhy.ledger.service

import com.hhyhhy.ledger.model.Bill
import com.hhyhhy.ledger.pojo.BillThinDTO
import com.hhyhhy.ledger.repository.BillRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.hhyhhy.ledger.model.Record
import com.hhyhhy.ledger.model.User
import com.hhyhhy.ledger.pojo.BillDTO
import com.hhyhhy.ledger.pojo.RecordDTO
import com.hhyhhy.ledger.pojo.UserDTO
import com.hhyhhy.ledger.repository.UserRepository
import com.hhyhhy.ledger.unwrap
import org.bson.types.ObjectId
import java.time.format.DateTimeFormatter

@Service
class BillService @Autowired constructor(val billRepository: BillRepository, val userRepository: UserRepository) {
    fun allThin(): List<BillThinDTO> {
        return billRepository.findAllThin().map { BillThinDTO(it) }
    }

    fun create(name: String): String {
        val bill = Bill(name = name)
        billRepository.save(bill)
        return bill.id.toHexString()
    }

    fun pushRecord(id: String, record: Record) {
        billRepository.pushRecord(id, record)
    }

    private fun getUserDTOById(cache: HashMap<String, User?>, id: ObjectId): UserDTO {
        val user = cache.getOrPut(id.toHexString()) {
            userRepository.findById(id.toHexString()).unwrap()
        }

        return if (user != null) UserDTO(user) else UserDTO(id.toHexString())
    }

    fun findById(id: String): BillDTO? {
        val bill = billRepository.findById(id).unwrap() ?: return null
        val userCache = HashMap<String, User?>()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val records = mutableListOf<RecordDTO>()

        for (record in bill.records) {
            val creatorDTO = getUserDTOById(userCache, record.creatorId)

            val consumers = mutableListOf<UserDTO>()
            for (uid in record.consumerIds) {
                val consumer = getUserDTOById(userCache, uid)
                consumers.add(consumer)
            }

            val recordDTO = RecordDTO(
                id = record.id.toHexString(),
                name = record.name,
                date = record.date.format(formatter),
                expense = record.expense,
                amountFen = record.amountFen,
                creator = creatorDTO,
                consumers = consumers
            )
            records.add(recordDTO)
        }

        return BillDTO(bill.id.toHexString(), bill.name, records)
    }
}