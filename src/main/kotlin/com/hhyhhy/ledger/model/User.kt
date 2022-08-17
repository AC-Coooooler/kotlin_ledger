package com.hhyhhy.ledger.model

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.text

object Users : Table<User>("users") {
    val id = long("id").primaryKey().bindTo { it.id }

    val name = text("name").bindTo { it.name }
}

interface User : Entity<User> {
    companion object : Entity.Factory<User>()

    val id: Long

    var name: String
}

val Database.users get() = sequenceOf(Users)
