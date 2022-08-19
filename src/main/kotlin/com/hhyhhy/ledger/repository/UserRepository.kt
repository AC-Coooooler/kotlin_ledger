package com.hhyhhy.ledger.repository

import com.hhyhhy.ledger.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    @Query(value = "{ '_id' : ?0 }")
    fun findOneById(id: String): User?
}