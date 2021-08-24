package com.zup.william.detalhamento

import com.william.ConsultaChavePixResponse
import java.time.LocalDateTime


class ConsultaChavePixResponseDTO(
    response: ConsultaChavePixResponse

) {
    val tipoChave = response.tipoChave
    val valorChave = response.valorChave

    val idCliente = response.idCliente
    val pixId = response.pixId
    val dadosConta = mapOf(

        Pair("nomeInstituicao", response.dadosConta.nomeInstituicao),
        Pair("agencia", response.dadosConta.agencia),
        Pair("numeroConta", response.dadosConta.numeroConta),
        Pair(("tipoConta"), response.dadosConta.tipoConta)
    )

    val dadosPessoais = mapOf(
        Pair("nome", response.dadosPessoais.nome),
        Pair("cpf", response.dadosPessoais.cpf),
    )

    val criadoEm = LocalDateTime.parse(response.criadoEm)


    override fun toString(): String {
        return "ConsultaChavePixResponseDTO(tipoChave=$tipoChave, valorChave='$valorChave', idCliente='$idCliente', pixId='$pixId', dadosConta=$dadosConta, dadosPessoais=$dadosPessoais, criadoEm='$criadoEm')"
    }


}

