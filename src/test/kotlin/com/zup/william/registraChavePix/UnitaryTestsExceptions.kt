package com.zup.william.registraChavePix

import com.zup.william.exception.GlobalExceptionHandler
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class UnitaryTestsExceptions {
    val request = HttpRequest.GET<Any>("/")

    @Test
    internal fun `deve retornar 404 quando chave nao for encontrada`() {

        val mensagem = "nao encontrado"
        val notFound = StatusRuntimeException(Status.NOT_FOUND.withDescription(mensagem))

        val resposta = GlobalExceptionHandler().handle(request, notFound)

        assertEquals(HttpStatus.NOT_FOUND, resposta.status)
        assertNotNull(resposta.body())
        assertEquals(mensagem, (resposta.body() as JsonError).message)
    }

    @Test
    internal fun `deve retornar 422 quando chave já esta cadastrada`() {

        val mensagem = "chave já existe"
        val alreadyExists = StatusRuntimeException(Status.ALREADY_EXISTS.withDescription(mensagem))

        val resposta = GlobalExceptionHandler().handle(request, alreadyExists)


        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resposta.status)
        assertNotNull(resposta.body())
        assertEquals(mensagem, (resposta.body() as JsonError).message)
    }

    @Test
    internal fun `deve retornar 400 quando dados da requisiçao for invalido`() {

        val mensagem = "Dados inválidos"
        val invalid = StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription(mensagem))

        val resposta = GlobalExceptionHandler().handle(request, invalid)


        assertEquals(HttpStatus.BAD_REQUEST, resposta.status)
        assertNotNull(resposta.body())
        assertEquals(mensagem, (resposta.body() as JsonError).message)
    }

    @Test
    internal fun `deve retornar 500 para qualquer outro erro`() {

        val mensagem = "Erro desconhecido"
        val invalid = StatusRuntimeException(Status.INTERNAL.withDescription(mensagem))

        val resposta = GlobalExceptionHandler().handle(request, invalid)

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resposta.status)
        assertNotNull(resposta.body())
        assertEquals(mensagem, (resposta.body() as JsonError).message)
    }
}