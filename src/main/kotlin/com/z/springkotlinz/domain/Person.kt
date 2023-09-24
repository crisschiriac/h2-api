package com.z.springkotlinz.domain

import jakarta.persistence.*

@Entity
data class Person(
        @Id
        @SequenceGenerator(name = PERSON_SEQUENCE, sequenceName = PERSON_SEQUENCE, initialValue = 1, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PERSON_SEQUENCE)
        val personId:Long = 1,
        //@Column(name = )
        var name:String = "",
        var lastName:String? = null,
        var password:String = "",

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "person_preference",
                joinColumns = [JoinColumn(name = "person_id", referencedColumnName = "personId")],
                inverseJoinColumns = [JoinColumn(name = "preference_id", referencedColumnName = "preferenceId")]
        )
        var preferences: MutableSet<Preference> = hashSetOf()

        //var preferences: MutableList<Preference> = mutableListOf()
)

{
        companion object {
                const val PERSON_SEQUENCE: String = "PERSON_SEQUENCE"
        }
}
