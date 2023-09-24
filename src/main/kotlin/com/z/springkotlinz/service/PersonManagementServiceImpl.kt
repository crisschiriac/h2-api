package com.z.springkotlinz.service

import com.z.springkotlinz.dao.PersonDao
import com.z.springkotlinz.dao.PreferenceDao
import com.z.springkotlinz.domain.Person
import com.z.springkotlinz.domain.Preference
import com.z.springkotlinz.dto.AddPersonRequest
import com.z.springkotlinz.dto.PersonResponse
import com.z.springkotlinz.dto.UpdatePersonRequest
import com.z.springkotlinz.transformer.AddPersonRequestTransformer
import com.z.springkotlinz.transformer.toPersonResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonManagementServiceImpl(private val personDao: PersonDao,
                                  private val preferenceDao: PreferenceDao,
                                  private val addPersonRequestTransformer: AddPersonRequestTransformer) : PersonManagementService {
    override fun findById(personId: Long): PersonResponse? = this.findPersonById(personId).toPersonResponse()

    override fun findAll(): List<PersonResponse> = this.personDao.findAll().map(Person::toPersonResponse)

    override fun save(addPersonRequest: AddPersonRequest): PersonResponse {
        return this.saveOrUpdate(
                addPersonRequestTransformer.transform(addPersonRequest)
        )
    }

    override fun update(updatePersonRequest: UpdatePersonRequest): PersonResponse {
        val person = this.findPersonById(updatePersonRequest.personId) ?: throw IllegalStateException("${updatePersonRequest.personId}: not found")

        return this.saveOrUpdate(person.apply {
            this.name = updatePersonRequest.name
            this.lastName = updatePersonRequest.lastName
            this.password = updatePersonRequest.password
            /*
            this.preferences = updatePersonRequest.preferences
            */
        })
    }

    override fun deleteById(personId: Long) {
        this.personDao.deleteById(personId)
    }

    private fun findPersonById(personId: Long): Person? = this.personDao.findByIdOrNull(personId)

    private fun saveOrUpdate(person: Person): PersonResponse = this.personDao.save(person).toPersonResponse()

    fun assignPreferenceToPerson(personId: Long, preferenceId: Long): Person{
        /*
        public Employee assignProjectToEmployee(Long empId, Long projectId)
        {
        Set<Project> projectSet = null;
        Employee employee = employeeRepository.findById(empId).get();
        Project project = projectRepository.findById(projectId).get();
        projectSet = employee.getAssignedProjects();
        projectSet.add(project);
        employee.setAssignedProjects(projectSet);
        return employeeRepository.save(employee);
        }
         */


        var preferenceSet: MutableSet<Preference>? = null
        val person = personDao.findById(personId).get()
        val preference = preferenceDao.findById(preferenceId).get()
        preferenceSet = person.preferences.toMutableSet()
        preferenceSet.add(preference)
        person.preferences = preferenceSet
        return personDao.save(person)

    }
}