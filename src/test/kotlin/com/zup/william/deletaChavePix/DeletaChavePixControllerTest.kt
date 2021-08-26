package com.zup.william.deletaChavePix

import com.william.ChavePixServiceRemoveGrpc
import com.william.EmptyReturn
import io.micronaut.http.HttpRequest.DELETE
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*


@MicronautTest
internal class DeletaChavePixControllerTest {

//
//    @field:Inject
//    @field:Client("/")
//    lateinit var client: HttpClient
//
//    @field:Inject
//    lateinit var deletaChaveService: ChavePixServiceRemoveGrpc.ChavePixServiceRemoveBlockingStub
//
//    @Test
//    fun `remove uma chave com sucesso`() {
//
//        val clientId = UUID.randomUUID().toString()
//        val pixId = UUID.randomUUID().toString()
//
//        given(deletaChaveService.remove(Mockito.any())).willReturn(EmptyReturn.newBuilder().build())
//        val body = RemoveChavePixRequestDto(clientId, pixId)
//
//
//        val response =
//            client.toBlocking().exchange(
//                DELETE("/api/chave/api", body),
//                RemoveChavePixRequestDto::class.java
//            )
//
//        println(response)
//    }

}
//
//@Factory
//@Replaces(factory = KeyManagerGrpcFactory::class)
//internal class MockitoSubFactory {
//
//    //Mockando a classe ChavePixServiceRegistraGrpc
//    @Singleton
//    fun stubMock() = Mockito.mock(ChavePixServiceRemoveGrpc.ChavePixServiceRemoveBlockingStub::class.java)
//
//
//}