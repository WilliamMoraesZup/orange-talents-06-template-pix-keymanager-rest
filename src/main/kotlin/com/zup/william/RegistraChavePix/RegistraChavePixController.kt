package com.zup.william.RegistraChavePix

import com.william.ChavePixServiceRegistraGrpc
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid


@Controller
class RegistraChavePixController(
    private val chavePixServiceRegistra: ChavePixServiceRegistraGrpc.ChavePixServiceRegistraBlockingStub
) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Post("/api/chave/{clientId}/api")
    fun registra(clientId: String,  @Body request: CadastraChavePixRequestDTO): HttpResponse<Any> {

        val grpcRequest = request.toModelGrpc(clientId)


        return try {
            LOGGER.info("Chamando o chavePixServiceRegistra... ")
            val grpcResponse = chavePixServiceRegistra.registra(grpcRequest)
            LOGGER.info("Retorno: ${grpcResponse} ")
            HttpResponse.ok()


        } catch (e: StatusRuntimeException) {
            LOGGER.warn("A chave já se encontra registrada !")
            HttpResponse.unprocessableEntity()
        } catch (e: Exception) {
            LOGGER.warn("Outro erro qualquer.. ${e.message}")
            HttpResponse.badRequest(e.message)
        }

    }
}