package com.workshops.resto.util

interface ModelMapper<Domain, Layer> : LayerMapper<Domain, Layer>, DomainMapper<Domain, Layer>