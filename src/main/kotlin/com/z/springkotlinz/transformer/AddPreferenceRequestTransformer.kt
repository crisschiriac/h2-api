package com.z.springkotlinz.transformer

import com.z.springkotlinz.domain.Preference
import com.z.springkotlinz.dto.AddPreferenceRequest
import org.springframework.stereotype.Component

@Component
class AddPreferenceRequestTransformer: Transformer<AddPreferenceRequest, Preference> {
    override fun transform(source: AddPreferenceRequest): Preference {
        return Preference(
                name = source.name
                /*
                people = source.people
                 */
        )
    }
}