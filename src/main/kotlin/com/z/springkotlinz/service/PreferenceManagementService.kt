package com.z.springkotlinz.service

import com.z.springkotlinz.dto.AddPreferenceRequest
import com.z.springkotlinz.dto.PreferenceResponse
import com.z.springkotlinz.dto.UpdatePreferenceRequest

interface PreferenceManagementService {
    fun findById(preferenceId: Long): PreferenceResponse?
    fun findAll(): List<PreferenceResponse>
    fun save(addPreferenceRequest: AddPreferenceRequest): PreferenceResponse
    fun update(updatePreferenceRequest: UpdatePreferenceRequest): PreferenceResponse
    fun deleteById(preferenceId:Long)
}