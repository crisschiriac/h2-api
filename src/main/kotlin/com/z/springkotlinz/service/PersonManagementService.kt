package com.z.springkotlinz.service

import com.z.springkotlinz.dto.AddPersonRequest
import com.z.springkotlinz.dto.PersonResponse
import com.z.springkotlinz.dto.UpdatePersonRequest

interface PersonManagementService {
    fun findById(personId:Long): PersonResponse?
    fun findAll(): List<PersonResponse>
    fun save(addPersonRequest: AddPersonRequest): PersonResponse
    fun update(updatePersonRequest: UpdatePersonRequest): PersonResponse
    fun deleteById(personId:Long)
}