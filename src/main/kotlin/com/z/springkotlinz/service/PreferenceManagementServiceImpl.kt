package com.z.springkotlinz.service

import com.z.springkotlinz.dao.PreferenceDao
import com.z.springkotlinz.domain.Preference
import com.z.springkotlinz.dto.AddPreferenceRequest
import com.z.springkotlinz.dto.PreferenceResponse
import com.z.springkotlinz.dto.UpdatePreferenceRequest
import com.z.springkotlinz.transformer.AddPreferenceRequestTransformer
import com.z.springkotlinz.transformer.toPreferenceResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PreferenceManagementServiceImpl(private val preferenceDao: PreferenceDao,
                                      private val addPreferenceRequestTransformer: AddPreferenceRequestTransformer) : PreferenceManagementService {
    override fun findById(preferenceId: Long): PreferenceResponse? = this.findPreferenceById(preferenceId).toPreferenceResponse()

    override fun findAll(): List<PreferenceResponse> = this.preferenceDao.findAll().map(Preference::toPreferenceResponse)

    override fun save(addPreferenceRequest: AddPreferenceRequest): PreferenceResponse {
        return this.saveOrUpdate(
                addPreferenceRequestTransformer.transform(addPreferenceRequest)
        )
    }

    override fun update(updatePreferenceRequest: UpdatePreferenceRequest): PreferenceResponse {
        val preference = this.findPreferenceById(updatePreferenceRequest.preferenceId) ?: throw IllegalStateException("${updatePreferenceRequest.preferenceId}: not found")

        return this.saveOrUpdate(preference.apply {
            this.name = updatePreferenceRequest.name
            /*
            this.people = updatePreferenceRequest.people
             */
        })
    }

    override fun deleteById(preferenceId: Long) {
        this.preferenceDao.deleteById(preferenceId)
    }

    private fun findPreferenceById(preferenceId: Long): Preference? = this.preferenceDao.findByIdOrNull(preferenceId)

    private fun saveOrUpdate(preference: Preference): PreferenceResponse = this.preferenceDao.save(preference).toPreferenceResponse()
}