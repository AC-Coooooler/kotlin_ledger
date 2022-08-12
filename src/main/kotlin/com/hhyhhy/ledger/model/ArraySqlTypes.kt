package com.hhyhhy.ledger.model

import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.SqlType
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

typealias LongArray = Array<Long>

fun BaseTable<*>.longArray(name: String): Column<LongArray> {
    return registerColumn(name, LongArraySqlType)
}

object LongArraySqlType : SqlType<LongArray>(Types.ARRAY, "bigint[]") {
    override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: LongArray) {
        ps.setObject(index, parameter)
    }

    @Suppress("UNCHECKED_CAST")
    override fun doGetResult(rs: ResultSet, index: Int): LongArray? {
        val sqlArray = rs.getArray(index) ?: return null
        try {
            val objectArray = sqlArray.array as Array<Any>?
            return objectArray?.map { it as Long }?.toTypedArray()
        } finally {
            sqlArray.free()
        }
    }
}