package com.hhyhhy.ledger.repository

import com.hhyhhy.ledger.model.Bill
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BillRepository : MongoRepository<Bill, String> {
    @Query(value = "{}", fields = "{'_id': 1, 'name': 1}")
    fun findAllThin(): List<Bill>
}