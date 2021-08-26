package com.zup.william.registraChavePix

import com.william.CadastraChavePixResponse
import com.william.ChavePixServiceRegistraGrpc
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*

@MicronautTest
internal class RegistraChavePixControllerTest {

    @field:Inject
    lateinit var novaChaveService: ChavePixServiceRegistraGrpc.ChavePixServiceRegistraBlockingStub


    //Cliente pra fazer a requisição
    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient


    //HAPPY PATH
    @Test
    fun `deve registrar uma nova chave pix sem erro`() {

        val clientId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()

        given(novaChaveService.registra(Mockito.any()))
            .willReturn(
                CadastraChavePixResponse.newBuilder()
                    .setPixId(pixId)
                    .setClienteId(clientId).build()
            )
        val body = CadastraChavePixRequestDTO(TipoDaChaveEnum.CHAVE_ALEATORIA, "", TipoDaContaEnum.CONTA_CORRENTE)

        val request = POST("api/chave/${clientId}/api", body)
        val response = client.toBlocking().exchange(request, CadastraChavePixRequestDTO::class.java)

        assertEquals(response.status, HttpStatus.CREATED)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location").contains(pixId))


    }


}

@Factory
@Replaces(KeyManagerGrpcFactory::class)
internal class MockitoSubFactory {

    //Mockando a classe ChavePixServiceRegistraGrpc
    @Singleton
    fun stubMock() = Mockito.mock(ChavePixServiceRegistraGrpc.ChavePixServiceRegistraBlockingStub::class.java)


}