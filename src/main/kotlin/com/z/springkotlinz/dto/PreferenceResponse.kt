package com.z.springkotlinz.dto

import com.z.springkotlinz.domain.Person

data class PreferenceResponse(val preferenceId:Long, val name:String, val people: MutableSet<Person>)
