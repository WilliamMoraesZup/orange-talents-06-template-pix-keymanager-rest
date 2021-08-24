package com.zup.william.listaChavePix

import com.william.ChavePixServiceListaChavesClienteGrpc
import com.william.ListaChavesClienteRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get


@Controller
class ListaChavePixController(private val listagem: ChavePixServiceListaChavesClienteGrpc.ChavePixServiceListaChavesClienteBlockingStub) {


    @Get("/api/consulta/{clienteId}/pix")
    fun lista(clienteId: String): HttpResponse<Any> {


        try {
            val listaChavesCliente = listagem.listaChavesCliente(
                ListaChavesClienteRequest.newBuilder().setClienteId(clienteId).build()
            )
            val map = listaChavesCliente.listaChavesList.map { ListaChaveResponseDTO(it) }

            return HttpResponse.ok(map)
        } catch (e: StatusRuntimeException) {

            when (e.status.code) {
                Status.NOT_FOUND.code -> return HttpResponse.notFound("Cliente não encontrado")
            }
        }
        return HttpResponse.badRequest()

    }

}