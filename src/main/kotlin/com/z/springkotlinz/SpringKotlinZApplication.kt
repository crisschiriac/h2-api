package com.z.springkotlinz

import com.z.springkotlinz.domain.Person
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class SpringKotlinZApplication{

	@GetMapping
	fun hi(): Person {
		return Person(lastName = "Lopez", name = "Mariano", password = "marianolopez")
	}
}

fun main(args: Array<String>) {
	runApplication<SpringKotlinZApplication>(*args)
}
