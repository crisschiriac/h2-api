package com.z.springkotlinz.resource

import com.z.springkotlinz.dto.AddPreferenceRequest
import com.z.springkotlinz.dto.PreferenceResponse
import com.z.springkotlinz.dto.UpdatePreferenceRequest
import org.springframework.http.ResponseEntity

interface PreferenceResource {
    fun findById(preferenceId:Long): ResponseEntity<PreferenceResponse?>
    fun findAll(): ResponseEntity<List<PreferenceResponse>>
    fun save(addPreferenceRequest: AddPreferenceRequest): ResponseEntity<PreferenceResponse>
    fun update(updatePreferenceRequest: UpdatePreferenceRequest): ResponseEntity<PreferenceResponse>
    fun deleteById(preferenceId: Long): ResponseEntity<Unit>
}