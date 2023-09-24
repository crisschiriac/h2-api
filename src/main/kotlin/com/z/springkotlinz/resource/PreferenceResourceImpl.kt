package com.z.springkotlinz.resource

import com.z.springkotlinz.dto.AddPreferenceRequest
import com.z.springkotlinz.dto.PreferenceResponse
import com.z.springkotlinz.dto.UpdatePreferenceRequest
import com.z.springkotlinz.resource.PreferenceResourceImpl.Companion.BASE_PREFERENCE_URL
import com.z.springkotlinz.service.PreferenceManagementService
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
@RequestMapping(value = [BASE_PREFERENCE_URL])
class PreferenceResourceImpl(private val preferenceManagementService: PreferenceManagementService) : PreferenceResource {
    @GetMapping("/{preferenceId}")
    override fun findById(@PathVariable preferenceId: Long): ResponseEntity<PreferenceResponse?> {
        val preferenceResponse: PreferenceResponse? = this.preferenceManagementService.findById(preferenceId)
        return ResponseEntity.status(HttpStatus.OK).body(preferenceResponse)
    }

    @GetMapping
    override fun findAll(): ResponseEntity<List<PreferenceResponse>> = ResponseEntity.ok(this.preferenceManagementService.findAll())

    @PostMapping
    override fun save(@RequestBody addPreferenceRequest: AddPreferenceRequest): ResponseEntity<PreferenceResponse> {
        val preferenceResponse = this.preferenceManagementService.save(addPreferenceRequest)
        return ResponseEntity
                .created(URI.create(BASE_PREFERENCE_URL.plus("/${preferenceResponse.preferenceId}")))
                .body(preferenceResponse)
    }

    @PutMapping
    override fun update(@RequestBody updatePreferenceRequest: UpdatePreferenceRequest): ResponseEntity<PreferenceResponse> {
        return ResponseEntity.ok(this.preferenceManagementService.update(updatePreferenceRequest))
    }

    @DeleteMapping("/{preferenceId}")
    override fun deleteById(@PathVariable preferenceId: Long): ResponseEntity<Unit> {
        this.preferenceManagementService.deleteById(preferenceId)
        return ResponseEntity.noContent().build()
    }

    companion object {
        const val BASE_PREFERENCE_URL: String = "/api/v1/preference"
    }
}