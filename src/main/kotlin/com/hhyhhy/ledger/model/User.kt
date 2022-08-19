package com.hhyhhy.ledger.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(val id: ObjectId = ObjectId.get(), val name: String)