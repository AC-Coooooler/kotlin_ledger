package com.hhyhhy.ledger.repository

import com.hhyhhy.ledger.model.Bill
import com.hhyhhy.ledger.model.Record
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.Update
import org.springframework.stereotype.Repository

@Repository
interface BillRepository : MongoRepository<Bill, String> {
    @Query(value = "{}", fields = "{'_id': 1, 'name': 1}")
    fun findAllThin(): List<Bill>

    @Query(value = "{'_id': ?0}")
    @Update(update = "{'\$push': {'records': ?1}}")
    fun pushRecord(id: String, Record: Record)
}