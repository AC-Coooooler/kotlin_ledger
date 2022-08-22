package com.hhyhhy.ledger.service

import com.hhyhhy.ledger.model.User
import com.hhyhhy.ledger.pojo.UserDTO
import com.hhyhhy.ledger.repository.UserRepository
import com.hhyhhy.ledger.unwrap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(val userRepository: UserRepository) {
    fun all(): List<UserDTO> {
        return userRepository.findAll().map { UserDTO(it) }
    }

    fun find(id: String): UserDTO? {
        val user = userRepository.findById(id).unwrap()?: return null
        return UserDTO(user)
    }

    fun create(name: String): String {
        val user = User(name = name)
        return userRepository.save(user).id.toHexString()
    }
}