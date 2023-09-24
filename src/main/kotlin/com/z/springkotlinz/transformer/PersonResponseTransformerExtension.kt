package com.z.springkotlinz.transformer

import com.z.springkotlinz.domain.Person
import com.z.springkotlinz.domain.Preference
import com.z.springkotlinz.dto.PersonResponse

fun Person?.toPersonResponse (): PersonResponse {
    return PersonResponse(
            personId = this?.personId ?: 1L,
            fullName = "${this?.lastName}, ${this?.name}",
            password = this?.password ?: "",
            preferences = this?.preferences ?:HashSet<Preference>()

            /*
            preferences = this?.preferences ?: MutableList<Preference>(0) { Preference() }
             */
    )
}