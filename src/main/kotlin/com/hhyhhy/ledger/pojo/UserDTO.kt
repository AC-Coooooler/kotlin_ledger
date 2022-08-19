package com.hhyhhy.ledger.pojo

import com.hhyhhy.ledger.model.User

data class UserDTO(val id: String, val name: String) {
    constructor(user: User) : this(user.id.toHexString(), user.name)
}