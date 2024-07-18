package ru.oleynik.otus.api.gateway.config

import feign.codec.Decoder
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

class FeignConfig {

    @Bean
    fun encoder(): Encoder {
        return SpringFormEncoder(
            SpringEncoder { HttpMessageConverters(RestTemplate().messageConverters) }
        )
    }

    @Bean
    fun decoder(): Decoder {
        return SpringDecoder { HttpMessageConverters(RestTemplate().messageConverters) }
    }

}