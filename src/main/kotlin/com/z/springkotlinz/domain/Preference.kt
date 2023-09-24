package com.z.springkotlinz.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Preference(
        @Id
        @SequenceGenerator(name = PREFERENCE_SEQUENCE, sequenceName = PREFERENCE_SEQUENCE, initialValue = 1, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PREFERENCE_SEQUENCE)
        val preferenceId:Long = 1,
        //@Column(name = )
        var name:String = "",

        @JsonIgnore
        @ManyToMany(mappedBy = "preferences")
        var people: MutableSet<Person> = hashSetOf()

        //var people: MutableList<Person> = mutableListOf()
)

{
    companion object {
        const val PREFERENCE_SEQUENCE: String = "PREFERENCE_SEQUENCE"
    }
}
