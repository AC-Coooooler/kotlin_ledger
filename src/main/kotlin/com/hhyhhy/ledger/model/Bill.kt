package com.hhyhhy.ledger.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.text

object Bills : Table<Bill>("bills") {
    val id = long("id").primaryKey().bindTo { it.id }

    val name = text("name").bindTo { it.name }
}

interface Bill : Entity<Bill> {
    companion object : Entity.Factory<Bill>()

    val id: Long

    var name: String
}