package com.workshops.resto.util

interface LayerMapper<Domain, Layer> {

    fun toLayer(domain: Domain): Layer
}