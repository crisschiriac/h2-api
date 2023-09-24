package com.z.springkotlinz.resource

import com.z.springkotlinz.domain.Person
import com.z.springkotlinz.dto.AddPersonRequest
import com.z.springkotlinz.dto.PersonResponse
import com.z.springkotlinz.dto.UpdatePersonRequest
import com.z.springkotlinz.resource.PersonResourceImpl.Companion.BASE_PERSON_URL
import com.z.springkotlinz.service.PersonManagementService
import com.z.springkotlinz.service.PersonManagementServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(value = [BASE_PERSON_URL])
class PersonResourceImpl(private val personManagementService: PersonManagementService,
                         private val personManagementServiceImpl: PersonManagementServiceImpl) : PersonResource {
    @GetMapping("/{personId}")
    override fun findById(@PathVariable personId: Long): ResponseEntity<PersonResponse?> {
        val personResponse: PersonResponse? = this.personManagementService.findById(personId)
        return ResponseEntity.status(HttpStatus.OK).body(personResponse)
    }

    @GetMapping
    override fun findAll(): ResponseEntity<List<PersonResponse>> = ResponseEntity.ok(this.personManagementService.findAll())

    @PostMapping
    override fun save(@RequestBody addPersonRequest: AddPersonRequest): ResponseEntity<PersonResponse> {
        val personResponse = this.personManagementService.save(addPersonRequest)
        return ResponseEntity
                .created(URI.create(BASE_PERSON_URL.plus("/${personResponse.personId}")))
                .body(personResponse)
    }

    @PutMapping
    override fun update(@RequestBody updatePersonRequest: UpdatePersonRequest): ResponseEntity<PersonResponse> {
        return ResponseEntity.ok(this.personManagementService.update(updatePersonRequest))
    }

    @PutMapping("/{personId}/preference/{preferenceId}")
    private fun updatePersonPreference(@PathVariable personId: Long,
                                       @PathVariable preferenceId: Long): Person {
        return personManagementServiceImpl.assignPreferenceToPerson(personId, preferenceId)
    }

    @DeleteMapping("/{personId}")
    override fun deleteById(@PathVariable personId: Long): ResponseEntity<Unit> {
        this.personManagementService.deleteById(personId)
        return ResponseEntity.noContent().build()
    }

    companion object {
        const val BASE_PERSON_URL: String = "/api/v1/person"
    }
}