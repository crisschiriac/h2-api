package com.z.springkotlinz.transformer

import com.z.springkotlinz.domain.Person
import com.z.springkotlinz.domain.Preference
import com.z.springkotlinz.dto.PreferenceResponse

fun Preference?.toPreferenceResponse (): PreferenceResponse {
    return PreferenceResponse(
            preferenceId = this?.preferenceId ?: 1L,
            name = "${this?.name}",
            people = this?.people ?:HashSet<Person>()

            /*
            people = this?.people ?: MutableList<Person>(0) { Person() }
             */
    )
}