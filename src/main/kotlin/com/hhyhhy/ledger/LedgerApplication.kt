package com.hhyhhy.ledger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class LedgerApplication

fun main(args: Array<String>) {
	runApplication<LedgerApplication>(*args)
}

fun <T> Optional<T>.unwrap(): T? = orElse(null)
