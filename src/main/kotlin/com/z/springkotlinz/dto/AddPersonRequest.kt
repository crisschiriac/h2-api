package com.z.springkotlinz.dto

import com.z.springkotlinz.domain.Preference

data class AddPersonRequest(val name:String, val lastName:String? = null, val password:String
/*
, val preferences: MutableList<Preference>
*/
)
