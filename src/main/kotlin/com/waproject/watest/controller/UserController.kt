package com.waproject.watest.controller

import com.waproject.watest.model.User
import com.waproject.watest.repository.UserRepository
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
        private val repository: UserRepository
) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): ResponseEntity<User> {
        val user = repository.findById(id)
        if(user.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(user.get())
    }

    @PostMapping
    fun save(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody userRequest: User): ResponseEntity<User> {
        val user = repository.findById(id)
        if(user.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        BeanUtils.copyProperties(userRequest, user.get(), "id", "createdAt", "updatedAt")
        return ResponseEntity.ok(repository.save(user.get()))
    }

}
