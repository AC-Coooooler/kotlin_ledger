package com.hhyhhy.ledger.dao

import com.hhyhhy.ledger.model.Record
import com.hhyhhy.ledger.model.Records
import org.springframework.stereotype.Component

@Component
class RecordDao : BaseDao<Record, Records>(Records)