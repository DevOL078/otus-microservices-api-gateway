package ru.oleynik.otus.api.gateway.config

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.core.convert.converter.Converter
import org.springframework.security.converter.RsaKeyConverters
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.security.interfaces.RSAPrivateKey

@Component
@ConfigurationPropertiesBinding
class RsaPrivateKeyConverter : Converter<String?, RSAPrivateKey?> {
    override fun convert(source: String): RSAPrivateKey? {
        return RsaKeyConverters.pkcs8().convert(ByteArrayInputStream(source.toByteArray()))
    }
}
