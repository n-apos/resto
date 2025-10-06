package com.workshops.resto.util

interface DomainMapper<Domain, Layer> {

    fun toDomain(layer: Layer): Domain
}