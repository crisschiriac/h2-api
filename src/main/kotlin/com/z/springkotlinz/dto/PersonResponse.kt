package com.z.springkotlinz.dto

import com.z.springkotlinz.domain.Preference

data class PersonResponse(val personId:Long, val fullName:String, val password:String, val preferences: MutableSet<Preference>)
