package com.hhyhhy.ledger.model

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*
import java.time.LocalDate

object Records : Table<Record>("records") {
    val id = long("id").primaryKey().bindTo { it.id }

    val name = text("name").bindTo { it.name }

    val date = date("date").bindTo { it.date }

    val expense = boolean("expense").bindTo { it.expense }

    val amountFen = long("amount_fen").bindTo { it.amountFen }

    val creatorId = long("creator_id").bindTo { it.creatorId }

    val consumerIds = longArray("consumer_ids").bindTo { it.consumerIds }
}

interface Record : Entity<Record> {
    companion object: Entity.Factory<Record>()

    val id: Long

    var name: String

    var date: LocalDate

    var expense: Boolean

    var amountFen: Long

    var creatorId: Long

    var consumerIds: LongArray
}

val Database.records get() = sequenceOf(Records)