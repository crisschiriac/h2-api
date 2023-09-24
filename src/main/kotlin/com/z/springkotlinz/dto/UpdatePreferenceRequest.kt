package com.z.springkotlinz.dto

import com.z.springkotlinz.domain.Person

data class UpdatePreferenceRequest(val preferenceId: Long, val name:String
/*
, val people: MutableSet<Person>
 */
)
