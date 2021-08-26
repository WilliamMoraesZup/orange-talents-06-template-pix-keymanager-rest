package com.zup.william.deletaChavePix

import com.william.ChavePixServiceRemoveGrpc.ChavePixServiceRemoveBlockingStub
import com.william.EmptyReturn
import com.zup.william.registraChavePix.KeyManagerGrpcFactory
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest.DELETE
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import java.util.*


@MicronautTest
internal class DeletaChavePixControllerTest {

    @field:Inject
    lateinit var deletaChaveService: ChavePixServiceRemoveBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient


    @Test
    internal fun `remove uma chave com sucesso`() {

        val clientId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()

        given(deletaChaveService.remove(any())).willReturn(EmptyReturn.newBuilder().build())
        val body = RemoveChavePixRequestDto(clientId, pixId)


        val response =
            client.toBlocking().exchange(
                DELETE("/api/chave/api", body),
                Any::class.java
            )

        println(response)
    }

}

@Factory
@Replaces(factory = KeyManagerGrpcFactory::class)
internal class RemoveStubFactory {

    //Mockando a classe ChavePixServiceRegistraGrpc
    @Singleton
    fun deleta() = mock(ChavePixServiceRemoveBlockingStub::class.java)


}