package com.workshops.resto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class RestoMainApplication


@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
    runApplication<RestoMainApplication>()
}