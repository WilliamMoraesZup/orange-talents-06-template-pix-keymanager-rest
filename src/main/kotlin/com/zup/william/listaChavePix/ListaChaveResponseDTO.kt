package com.zup.william.listaChavePix

import com.william.ListaChavesClienteResponse
import io.micronaut.core.annotation.Introspected


@Introspected
class ListaChaveResponseDTO(response: ListaChavesClienteResponse.ChaveClienteLista) {

    val pixId = response.pixId
    val tipoDocumented = response.tipoDaChave
    val tipoDaConta = response.tipoDaConta
    val valorChave = response.valorChave
    val dataCriacao = response.dataCriacao
    override fun toString(): String {
        return "ListaChaveResponseDTO(pixId='$pixId', tipoDocumented=$tipoDocumented, tipoDaConta=$tipoDaConta, valorChave='$valorChave', dataCriacao='$dataCriacao')"
    }


}