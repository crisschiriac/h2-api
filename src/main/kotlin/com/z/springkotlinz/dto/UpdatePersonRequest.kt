package com.z.springkotlinz.dto

import com.z.springkotlinz.domain.Preference

data class UpdatePersonRequest(val personId: Long, val name:String, val lastName:String? = null, val password:String
/*
, val preferences: MutableSet<Preference>
 */
)
