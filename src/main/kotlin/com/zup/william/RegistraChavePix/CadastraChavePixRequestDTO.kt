package com.zup.william.RegistraChavePix

import com.william.CadastraChavePixRequest
import com.william.TipoDaChave
import com.william.TipoDaConta
import io.micronaut.core.annotation.Introspected
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ValidaTipoChave
@Introspected
data class CadastraChavePixRequestDTO(
    @field:NotNull val tipoDaChave: TipoDaChaveEnum?,
    @field:Size(max = 77) val valorDaChave: String?,
    @field:NotNull val tipoDaConta: TipoDaContaEnum?
) {


    fun toModelGrpc(idCliente_: String): CadastraChavePixRequest {

        return CadastraChavePixRequest.newBuilder()

            .setValorChave(valorDaChave ?: "")
            .setTipoDaConta(tipoDaConta?.tipo ?: TipoDaConta.UNKNOWN_TIPO_CONTA)
            .setTipoDaChave(tipoDaChave?.tipo ?: TipoDaChave.UNKNOWN_TIPO_CHAVE)
            .setIdCliente(idCliente_)
            .build()
    }

}

enum class TipoDaChaveEnum(val tipo: TipoDaChave) {

    CPF(TipoDaChave.CPF) {

        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            if (!chave.matches("^[0-9]{11}\$".toRegex())) {
                return false
            }
            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },

    CELULAR(TipoDaChave.CELULAR) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            return (chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex()))
        }
    },
    EMAIL(TipoDaChave.EMAIL) {
        override fun valida(chave: String?): Boolean {

            if (chave.isNullOrBlank()) {

                return false
            }

            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    CHAVE_ALEATORIA(TipoDaChave.CHAVE_ALEATORIA) {
        override fun valida(chave: String?) = chave.isNullOrBlank()
    };

    abstract fun valida(chave: String?): Boolean

}

enum class TipoDaContaEnum(val tipo: TipoDaConta) {
    CONTA_CORRENTE(TipoDaConta.CONTA_CORRENTE),
    CONTA_POUPANCA(TipoDaConta.CONTA_POUPANCA),
    UNKNOWN_TIPOCONTA(TipoDaConta.UNKNOWN_TIPO_CONTA)

}
