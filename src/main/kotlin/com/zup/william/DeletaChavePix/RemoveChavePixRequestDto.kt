package com.zup.william.DeletaChavePix

import com.william.RemoveChavePixRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Introspected
data class RemoveChavePixRequestDto(
    @field:NotBlank val clienteId: String,
    @field:NotBlank @field:Size(max = 77) val pixId: String
) {
    fun toGrpc(): RemoveChavePixRequest {
        return RemoveChavePixRequest.newBuilder().setPixId(pixId)
            .setClienteId(clienteId).build()


    }
}
