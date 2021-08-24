package com.zup.william.detalhamento

import com.william.ChavePixServiceConsultaGrpc
import com.william.ConsultaChavePixRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory

@Controller
class DetalhamentoController(private val detalhamento: ChavePixServiceConsultaGrpc.ChavePixServiceConsultaBlockingStub) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Get("/api/consulta/{clienteId}/pix/{pixId}")
    fun detalhamento(clienteId: String, pixId: String): HttpResponse<Any> {


        try {
            val pixResponse = detalhamento.consulta(
                ConsultaChavePixRequest.newBuilder()
                    .setPixIdRequest(
                        ConsultaChavePixRequest.ConsultaPorPixId.newBuilder()
                            .setClienteId(clienteId)
                            .setPixId(pixId).build()
                    ).build()
            )

            return HttpResponse.ok(ConsultaChavePixResponseDTO(pixResponse))
        } catch (e: StatusRuntimeException) {
            LOGGER.warn("Status Runtime capturado:  ${e.status.code}")


            when (e.status.code) {
                Status.NOT_FOUND.code -> return HttpResponse.notFound("Chave não encontrada")
            }

            return HttpResponse.badRequest()


        }
    }

}