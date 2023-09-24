package com.z.springkotlinz.dao

import com.z.springkotlinz.domain.Preference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PreferenceDao: JpaRepository<Preference, Long>