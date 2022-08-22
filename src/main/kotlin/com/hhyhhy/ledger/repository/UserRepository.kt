package com.hhyhhy.ledger.repository

import com.hhyhhy.ledger.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String>