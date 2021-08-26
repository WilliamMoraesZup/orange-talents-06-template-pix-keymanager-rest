package com.zup.william.registraChavePix

import com.william.ChavePixServiceRegistraGrpc
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory


@Controller
class RegistraChavePixController(
    private val chavePixServiceRegistra: ChavePixServiceRegistraGrpc.ChavePixServiceRegistraBlockingStub
) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Post("/api/chave/{clientId}/api")
    fun registra(clientId: String, @Body request: CadastraChavePixRequestDTO): HttpResponse<Any> {

        val grpcRequest = request.toModelGrpc(clientId)


        return try {
            LOGGER.info("Chamando o chavePixServiceRegistra... ")
            val grpcResponse = chavePixServiceRegistra.registra(grpcRequest)
            LOGGER.info("Retorno: ${grpcResponse} ")
            HttpResponse.created(HttpResponse.uri("/api/chave/$clientId/api/${grpcResponse.pixId}"))


        } catch (e: StatusRuntimeException) {
            when (e.status.code) {
                Status.ALREADY_EXISTS.code -> HttpResponse.unprocessableEntity<Any?>()
                    .also { LOGGER.warn("A chave já se encontra registrada !") }
                else -> HttpResponse.badRequest("Outro erro ${e.status.code}")
            }

        } catch (e: Exception) {
            LOGGER.warn("Outro erro qualquer.. ${e.message}")
            HttpResponse.badRequest(e.message)
        }

    }
}